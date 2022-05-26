package com.riu.users.infraestructure.mapper;

import com.riu.users.domain.model.User;
import com.riu.users.infraestructure.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserEntity convert(User user);

    User convert(UserEntity UserEntity);
}
