package com.nhom1.deep_sleeping.service;

import com.nhom1.deep_sleeping.dto.request.UpdateSleepTimeRequest;
import com.nhom1.deep_sleeping.model.SleepTime;
import com.nhom1.deep_sleeping.model.User;
import com.nhom1.deep_sleeping.repository.SleepTimeRepository;
import com.nhom1.deep_sleeping.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SleepSuggestionService {
    SleepTimeRepository sleepTimeRepository;
    UserRepository userRepository;

    public List<Float> getSleepHourByWeekAgo(Integer weekAgo) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not existed!")
        );

        LocalDate startDate = LocalDate.now().minusWeeks(weekAgo).with(DayOfWeek.MONDAY);
        LocalDate endDate = startDate.with(DayOfWeek.SUNDAY);

        List<SleepTime> sleepTimes = sleepTimeRepository.findBySleepTimeInDurationOfWeek(user.getId(), startDate, endDate);

        // Tạo danh sách mặc định với 7 giá trị 0
        List<Float> sleepHoursList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            sleepHoursList.add(0f);
        }

        // Gán giá trị sleepHours vào danh sách
        for (SleepTime sleepTime : sleepTimes) {
            int index = (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, sleepTime.getDate());
            if (index >= 0 && index < 7) {
                sleepHoursList.set(index, sleepTime.getSleepHours());
            }
        }

        return sleepHoursList;

    }

    public void updateSleepHour(UpdateSleepTimeRequest request) {
        Integer weekAgo = request.getWeekAgo();
        List<Float> sleepTimes = request.getSleepTimes();

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not existed!")
        );

        // Tính toán ngày đầu tuần dựa trên weekAgo
        LocalDate startDate = LocalDate.now().minusWeeks(weekAgo).with(DayOfWeek.MONDAY);
        LocalDate currentDay = LocalDate.now();

        // Lưu hoặc cập nhật từng ngày
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            if (!date.isBefore(currentDay))
                break;

            Float sleepHours = sleepTimes.get(i);

            // Tìm hoặc tạo mới bản ghi
            SleepTime record = sleepTimeRepository.findByUserIdAndDate(user.getId(), date)
                    .orElse(new SleepTime());
            record.setUserId(user.getId());
            record.setDate(date);
            record.setSleepHours(sleepHours);

            // Lưu bản ghi
            sleepTimeRepository.save(record);
        }
    }
}
