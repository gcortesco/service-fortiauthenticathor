package com.riu.users.infraestructure.repository.ddbb;

import com.riu.users.infraestructure.model.UserEntity;
import com.riu.users.infraestructure.repository.ddbb.impl.CustomUserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private CustomUserRepository sut;

    private UserEntity course = new UserEntity("gcortes", "Gonzalo");

    @BeforeEach
    public void setup() {
        sut.addUser(new UserEntity("Usuario 1", "Name 1"));
        sut.addUser(new UserEntity("Usuario 2", "Name 2"));
        sut.addUser(new UserEntity("Usuario 3", "Name 3"));
    }

    @Test
    public void shouldObtainData() {
        List<UserEntity> allCourses = sut.findAll();

        assertEquals(allCourses.get(0).getUsername(), "Usuario 1");
        assertEquals(allCourses.get(1).getUsername(), "Usuario 2");
        assertEquals(allCourses.get(2).getUsername(), "Usuario 3");
    }

    @Test
    public void shouldSaveData() {
        Optional<UserEntity> courseSave = sut.save(course);
        assertEquals(courseSave.get().getUsername(), "gcortes");
    }

}
