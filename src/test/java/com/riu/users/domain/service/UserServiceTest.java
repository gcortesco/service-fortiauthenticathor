package com.riu.users.domain.service;

import com.riu.users.domain.model.User;
import com.riu.users.domain.repository.UserRepository;
import com.riu.users.domain.service.impl.UserListAndCreator;
import com.riu.users.infraestructure.adapter.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class UserServiceTest {

    private UserRepository repository = mock(UserRepositoryImpl.class);

    private UserListAndCreator sut = new UserListAndCreator(repository);

    private User user = mock(User.class);

    @Test
    public void shouldGetAllUsers() {
        sut.getAllUsers();

        verify(repository).findAll();
    }

    @Test
    public void shouldSaveUser() {
        sut.saveUser(user);
        verify(repository).save(user);
    }


}
