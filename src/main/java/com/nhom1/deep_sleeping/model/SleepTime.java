package com.nhom1.deep_sleeping.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class SleepTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Float sleepHours;
    Long userId;

    @Column(unique = true)
    LocalDate date;
}
