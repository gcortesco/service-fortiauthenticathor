package com.riu.users.application.api.controller;

import com.riu.users.application.model.UserDto;
import com.riu.users.domain.model.User;
import com.riu.users.domain.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;


    @Test
    public void shouldGetAllUsers() {
        List<User> users =List.of(new User("username","name"));
        when(userService.getAllUsers()).thenReturn(users);
        List<UserDto> respone = userController.getAllUsers();
        assertEquals(respone.size(),1);
    }

    @Test
    public void shouldSaveUsersAndRetrieveInfo() {
        User user = new User("prueba", "prueba");
        when(userService.saveUser(user)).thenReturn(Optional.ofNullable(user));
        UserDto userDTO = new UserDto("prueba", "prueba");
        ResponseEntity<UserDto> response = userController.saveUser(userDTO);
        assertEquals(response.getStatusCode().value(),200);
        assertEquals(response.getBody().getName(),"prueba");
        assertEquals(response.getBody().getUsername(),"prueba");

    }

}
