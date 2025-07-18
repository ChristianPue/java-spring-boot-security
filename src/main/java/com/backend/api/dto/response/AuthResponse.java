package com.backend.api.dto.response;

public record AuthResponse(
  String token,
  String type,
  String username,
  String email,
  String role
) {}
