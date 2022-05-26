package com.riu.users.application.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = UserDTOValidator.class)
public @interface RobustUser {

    String message() default "Name and Username must not be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
