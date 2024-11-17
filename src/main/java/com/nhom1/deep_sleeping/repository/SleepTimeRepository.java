package com.nhom1.deep_sleeping.repository;

import com.nhom1.deep_sleeping.model.SleepTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SleepTimeRepository extends JpaRepository<SleepTime, Long> {
    Optional<SleepTime> findByUserIdAndDate(Long userId, LocalDate date);

    @Query("SELECT s FROM SleepTime s " +
            "WHERE s.userId = :userId " +
            "AND s.date BETWEEN :startDate AND :endDate " +
            "ORDER BY s.date ASC")
    List<SleepTime> findBySleepTimeInDurationOfWeek(@Param("userId") Long userId,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);
}
//lời gọi hàm cơ sở dữ liêu cho sleepsugesstion