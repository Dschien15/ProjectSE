package com.dongnv.deep_sleeping.dto.mapper;

import com.dongnv.deep_sleeping.dto.request.RegisterRequest;
import com.dongnv.deep_sleeping.dto.response.UserResponse;
import com.dongnv.deep_sleeping.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(RegisterRequest request);
    UserResponse toUserResponse(User user);
}
