package com.riu.users.infraestructure.repository.ddbb;

import com.riu.users.infraestructure.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface CustomUserRepository {

    List<UserEntity> findAll();

    Optional<UserEntity> save(UserEntity user);

    Optional<UserEntity> findByUsername(String username);
}
