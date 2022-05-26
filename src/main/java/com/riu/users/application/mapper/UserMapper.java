package com.riu.users.application.mapper;

import com.riu.users.application.model.UserDto;
import com.riu.users.domain.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User convert(UserDto userDTO);

    UserDto convert(User userRequest);
}
