package com.riu.users.domain.repository;

import com.riu.users.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> save(User course);

    Optional<User> findByUsername(String username);
}
