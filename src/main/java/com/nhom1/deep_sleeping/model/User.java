package com.nhom1.deep_sleeping.model;

import com.nhom1.deep_sleeping.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Column(unique = true)
    String email;
    String password;

    @Enumerated(EnumType.STRING)
    UserRole role;

    @PrePersist
    void setDefault() {
        if (role == null) {
            role = UserRole.USER;
        }
    }
}
