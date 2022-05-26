package com.riu.users.infraestructure.repository.ddbb;

import com.riu.users.infraestructure.model.UserEntity;
import com.riu.users.infraestructure.repository.ddbb.impl.CustomUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters=false)
@SpringBootTest
public class UserRepositoryImplIT {

    @Autowired
    public MockMvc mockMvc;

    CustomUserRepository userRepository = new CustomUserRepository();

    @BeforeEach
    public void setup(){
        userRepository.addUser(new UserEntity("gcortes", "Gonzalo"));
    }

    @Test
    public void shouldGetAllUsers() throws Exception {
        mockMvc.perform(
                get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



}
