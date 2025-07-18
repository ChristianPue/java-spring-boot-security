package com.backend.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
  @NotBlank(message = "El nombre de usuario es obligatorio.")
  @Size(min = 3, max = 20, message = "El nombre de usuario tiene que tener entre 3 y 20 caracteres.")
  String username,
  
  @NotBlank(message = "El correo es obligatorio.")
  @Email(message = "El correo no es válido.")
  String email,
  
  @NotBlank(message = "La contraseña es obligatoria.")
  @Size(min = 6, max = 40, message = "La contraseña tiene que tener entre 6 y 40 caracteres.")
  String password
) {}
