package com.nhom1.deep_sleeping.config;

import com.nhom1.deep_sleeping.constant.UserRole;
import com.nhom1.deep_sleeping.model.User;
import com.nhom1.deep_sleeping.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (!userRepository.existsByEmail("admin@admin.com")) {
                User user = User.builder()
                        .email("admin@admin.com")
                        .name("admin")
                        .password(new BCryptPasswordEncoder(10).encode("admin"))
                        .role(UserRole.ADMIN)
                        .build();
                userRepository.save(user);
                log.info("Create Admin Account successfully with email: admin@admin.com, password: admin");
            }
        };
    }
}
