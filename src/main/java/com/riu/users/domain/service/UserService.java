package com.riu.users.domain.service;

import com.riu.users.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> saveUser(User user);

	Optional<User> getByUsername(String username);

}
