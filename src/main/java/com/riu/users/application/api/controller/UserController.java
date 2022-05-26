package com.riu.users.application.api.controller;

import com.riu.users.application.api.UserApi;
import com.riu.users.application.mapper.UserMapper;
import com.riu.users.application.model.UserDto;
import com.riu.users.domain.model.User;
import com.riu.users.domain.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "User demo")
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    private final UserMapper mapper= Mappers.getMapper(UserMapper.class);


    public UserController() {}

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(mapper::convert).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<UserDto> getUserByUsername(String username) {
        return ResponseEntity.of(userService.getByUsername(username).map(mapper::convert));
    }

    @Override
    public ResponseEntity<UserDto> saveUser(UserDto userDTO) {
        User user = mapper.convert(userDTO);
        return ResponseEntity.of(userService.saveUser(user).map(mapper::convert));
    }

}
