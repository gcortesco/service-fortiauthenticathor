package com.riu.users.infraestructure.adapter.repository;

import com.riu.users.domain.model.User;
import com.riu.users.domain.repository.UserRepository;
import com.riu.users.infraestructure.mapper.UserMapper;
import com.riu.users.infraestructure.model.UserEntity;
import com.riu.users.infraestructure.repository.ddbb.CustomUserRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Autowired
    private CustomUserRepository customUserRespository;

    public UserRepositoryImpl(CustomUserRepository customUserRespository){
        this.customUserRespository=customUserRespository;
    }
    @Override
    public List<User> findAll() {
        return customUserRespository.findAll().stream().map(userMapper::convert).collect(Collectors.toList());
    }

    @Override
    public Optional<User> save(User user) {
        UserEntity userEntity = userMapper.convert(user);
        return customUserRespository.save(userEntity).map(userMapper::convert);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return customUserRespository.findByUsername(username).map(userMapper::convert);
    }
}
