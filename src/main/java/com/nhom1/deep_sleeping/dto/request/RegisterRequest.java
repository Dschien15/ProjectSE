package com.nhom1.deep_sleeping.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    @Size(min = 3, max = 20, message = "Invalid name")
    String name;

    @NotBlank(message = "Invalid email")
    @Email(message = "Invalid email")
    String email;

    @Size(min = 5, message = "Password must be at least 5 character")
    String password;
}
