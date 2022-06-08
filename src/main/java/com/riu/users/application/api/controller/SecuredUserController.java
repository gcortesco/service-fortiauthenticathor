package com.riu.users.application.api.controller;

import com.riu.users.application.api.UserApi;
import com.riu.users.application.mapper.UserMapper;
import com.riu.users.application.model.UserDto;
import com.riu.users.domain.model.User;
import com.riu.users.domain.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/secured/users")
@SecurityRequirements({
        @SecurityRequirement(name = "FortiAuthenticator"),
        @SecurityRequirement(name = "FortiAuthenticator2")
})
@Tag(name = "Secured user demo")
public class SecuredUserController implements UserApi {

    @Autowired
    private UserService userService;

    private final UserMapper mapper= Mappers.getMapper(UserMapper.class);


    public SecuredUserController() {}

    public SecuredUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(mapper::convert).collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByUsername(String username) {
        return ResponseEntity.of(userService.getByUsername(username).map(mapper::convert));
    }

    @Override
    @GetMapping("/save")
    public ResponseEntity<UserDto> saveUser(UserDto userDTO) {
        User user = mapper.convert(userDTO);
        return ResponseEntity.of(userService.saveUser(user).map(mapper::convert));
    }

}
