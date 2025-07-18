package com.backend.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.api.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUsername(String username);
  Optional<User> findByUsername(String username);

  boolean existsByEmail(String email);
  Optional<User> findByEmail(String email);
}
