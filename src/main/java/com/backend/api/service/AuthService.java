package com.backend.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.api.dto.request.LoginRequest;
import com.backend.api.dto.request.SignupRequest;
import com.backend.api.dto.response.AuthResponse;
import com.backend.api.exception.BadRequestException;
import com.backend.api.mapper.UserMapper;
import com.backend.api.model.entity.User;
import com.backend.api.model.enums.RoleType;
import com.backend.api.repository.UserRepository;
import com.backend.api.security.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  @Transactional
  public AuthResponse signup(SignupRequest request) {
    // Val 1: Dicho correo ya está en uso.
    if (userRepository.existsByEmail(request.email())) {
      throw new BadRequestException("El email ya está en uso.");
    }

    // Val 2: Dicho nombre de usuario ya está en uso.
    if (userRepository.existsByUsername(request.username())) {
      throw new BadRequestException("El nombre de usuario ya está en uso.");
    }

    // Convertimos el request a entidad.
    User user = UserMapper.toUser(request, RoleType.USER);
    
    // Encriptamos la contraseña.
    user.setPassword(passwordEncoder.encode(request.password()));

    userRepository.save(user);

    return UserMapper.toAuthResponse(user, jwtUtil.generateToken(user.getUsername()));
  }

  @Transactional
  public AuthResponse login(LoginRequest request) {
    // Val 1: No se encontró el nombre de usuario.
    User user = userRepository.findByUsername(request.username())
      .orElseThrow(() -> new BadRequestException("El nombre de usuario no existe."));
    
    // Val 2: La contraseña no es la misma.
    if (!passwordEncoder.matches(request.password(), user.getPassword())) {
      throw new BadRequestException("La contraseña es incorrecta.");
    }

    return UserMapper.toAuthResponse(user, jwtUtil.generateToken(user.getUsername()));
  }
}
