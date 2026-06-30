package com.util;

import javax.validation.*;
import java.util.Set;

public class ValidationUtil {

    private static final ValidatorFactory factory =
            Validation.buildDefaultValidatorFactory();

    private static final Validator validator =
            factory.getValidator();

    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        return validator.validate(object);
    }
}