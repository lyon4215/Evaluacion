package com.example.Evaluacion.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidationConfig {

    @Value("${password.regex}")
    private String passwordRegex;

    @Value("${password.regex.message}")
    private String passwordMessage;

    public String getPasswordRegex() {
        return passwordRegex;
    }

    public String getPasswordMessage() {
        return passwordMessage;
    }
}

