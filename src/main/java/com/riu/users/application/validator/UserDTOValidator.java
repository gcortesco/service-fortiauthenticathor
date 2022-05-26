package com.riu.users.application.validator;

import com.riu.users.application.model.UserDto;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;


public class UserDTOValidator implements ConstraintValidator<RobustUser, UserDto> {

    private static final String ERROR_EMPTY_NAME = "The name must not be empty";
    private static final String ERROR_EMPTY_USERNAME = "The username must not be empty";

    @Override
    public boolean isValid(UserDto value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        List<String> errors = new ArrayList<>();

        if(value.getName()==null || value.getName().equals("")){
            errors.add(ERROR_EMPTY_NAME);
        }

        if(value.getUsername()==null || value.getUsername().equals("")){
            errors.add(ERROR_EMPTY_USERNAME);
        }

        if (!CollectionUtils.isEmpty(errors)) {
            errors.forEach(error -> context.buildConstraintViolationWithTemplate(error).addConstraintViolation());
            return false;
        }

        return true;
    }
}
