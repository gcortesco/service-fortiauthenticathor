package com.riu.users.infraestructure.repository.ddbb.impl;

import com.riu.users.infraestructure.mapper.UserMapper;
import com.riu.users.infraestructure.model.UserEntity;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomUserRepository implements com.riu.users.infraestructure.repository.ddbb.CustomUserRepository {

    private List<UserEntity> users = new ArrayList<>();

    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Override
    public List<UserEntity> findAll() {
        return users;
    }

    @Override
    public Optional<UserEntity> save(UserEntity user) {
        return users.add(user) ? Optional.of(user) : Optional.of(null);
    }

    public void addUser(UserEntity course) {
        users.add(course);
    }

	@Override
	public Optional<UserEntity> findByUsername(String username) {
		return this.users.stream().filter(r->r.getUsername().equals(username)).findAny();
	}
}
