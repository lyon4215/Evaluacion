package com.example.Evaluacion.config;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String passwordRegex;
    private String passwordMessage;

    @Override
    public void initialize(Password constraintAnnotation) {
        // Establece las expresiones regulares y mensajes desde las propiedades
        this.passwordRegex = System.getProperty("password.regex", "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&.])[A-Za-z\\d@$!%*?&.]{8,}$");
        this.passwordMessage = System.getProperty("password.regex.message", "La contraseña debe tener al menos 8 caracteres, incluir al menos una letra, un número y un carácter especial.");
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        boolean matches = password.matches(passwordRegex);
        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(passwordMessage)
                    .addConstraintViolation();
        }
        return matches;
    }
}
