package com.riu.users.application.model;

import com.riu.users.application.validator.RobustUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@RobustUser
public class UserDto {

    public static final String EXAMPLE = "{\"name\":\"test\"}";
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String name;
}


