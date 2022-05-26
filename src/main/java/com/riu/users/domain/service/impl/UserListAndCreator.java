package com.riu.users.domain.service.impl;

import com.riu.users.domain.model.User;
import com.riu.users.domain.repository.UserRepository;
import com.riu.users.domain.service.UserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class UserListAndCreator implements UserService {

	private UserRepository repository;

	public UserListAndCreator() {}

	public UserListAndCreator(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public List<User> getAllUsers() {
		return repository.findAll();
	}
	@Override
	@Transactional
	public Optional<User> saveUser(User user) {
		return repository.save(user);
	}

	@Override
	@Transactional
	public Optional<User> getByUsername(String username) {
		return repository.findByUsername(username);
	}

}
