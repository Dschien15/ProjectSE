package com.nhom1.deep_sleeping.controller;

import com.nhom1.deep_sleeping.dto.request.UpdateSleepTimeRequest;
import com.nhom1.deep_sleeping.service.SleepSuggestionService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class SleepSuggestionController {
    SleepSuggestionService sleepSuggestionService;

    @GetMapping("/sleep-suggestion")
    String sleepSuggestion(@RequestParam(value = "weekAgo", defaultValue = "0") Integer weekAgo, Model model) {
        if (weekAgo < 0 || weekAgo > 3)
            return "redirect:/sleep-suggestion?weekAgo=0";

        List<Float> sleepHoursData = sleepSuggestionService.getSleepHourByWeekAgo(weekAgo);

        model.addAttribute("weekAgo", weekAgo);
        model.addAttribute("sleepHours", sleepHoursData);
        return "sleep-suggestion";
    }

    @PostMapping("/update-sleep-hours")
    @ResponseBody
    ResponseEntity<String> updateSleepHours(@Valid @RequestBody UpdateSleepTimeRequest request) {
        log.info(request.toString());
        sleepSuggestionService.updateSleepHour(request);

        return ResponseEntity.ok("Weekly sleep times updated successfully.");
    }
}
