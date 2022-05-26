package com.riu.users.application.mapper;

import com.riu.users.application.model.UserDto;
import com.riu.users.domain.model.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserMapperTest {

    User userExpected = new User("gcortes", "Gonzalo");
    UserDto userDTO = new UserDto("gcortes", "Gonzalo");
    UserMapper converter = Mappers.getMapper(UserMapper.class);


    @Test
    public void shouldConvertRequestToDomain() {
        User courseActual = converter.convert(userDTO);
        assertEquals(userExpected, courseActual);
    }

}
