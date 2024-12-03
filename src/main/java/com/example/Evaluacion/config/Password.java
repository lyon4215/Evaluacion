package com.example.Evaluacion.config;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "La contraseña debe tener al menos 8 caracteres, incluir al menos una letra, un número y un carácter especial.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

