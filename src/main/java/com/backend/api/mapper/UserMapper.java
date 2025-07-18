package com.backend.api.mapper;

import com.backend.api.dto.request.SignupRequest;
import com.backend.api.dto.response.AuthResponse;
import com.backend.api.model.entity.User;
import com.backend.api.model.enums.RoleType;

public class UserMapper {
  public static User toUser(SignupRequest signupRequest, RoleType role) {
    return User.builder()
      .username(signupRequest.username())
      .email(signupRequest.email())
      .password(signupRequest.password())
      .role(role)
      .build();
  }

  public static AuthResponse toAuthResponse(User user, String token) {
    return new AuthResponse(
      token,
      "Bearer",
      user.getUsername(),
      user.getEmail(),
      user.getRole().name()
    );
  }
}
