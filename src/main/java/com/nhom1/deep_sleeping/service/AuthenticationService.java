package com.nhom1.deep_sleeping.service;

import com.nhom1.deep_sleeping.dto.mapper.UserMapper;
import com.nhom1.deep_sleeping.dto.request.RegisterRequest;
import com.nhom1.deep_sleeping.dto.response.UserResponse;
import com.nhom1.deep_sleeping.model.User;
import com.nhom1.deep_sleeping.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse register(RegisterRequest registerRequest) {
        User user = userMapper.toUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("REGISTER SUCCESSFULLY!");
        return userMapper.toUserResponse(user);
    }
}
