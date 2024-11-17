package com.nhom1.deep_sleeping.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSleepTimeRequest {
    @NotNull(message = "Week ago must not be null")
    @Min(value = 0, message = "Week ago must be between 0 and 3")
    @Max(value = 3, message = "Week ago must be between 0 and 3")
    Integer weekAgo;

    @NotNull(message = "Sleep times must have exactly 7 values")
    @Size(min = 7, max = 7, message = "Sleep times must have exactly 7 values")
    List<@Min(value = 0, message = "Sleep time must be >= 0")
        @Max(value = 24, message = "Sleep time must be <= 24")
            Float> sleepTimes;
}
