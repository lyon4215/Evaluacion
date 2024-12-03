package com.example.Evaluacion.model;

import com.example.Evaluacion.config.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Email(message = "El correo debe tener un formato válido")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "El correo debe tener un formato válido y un dominio válido como '.com', '.net', '.cl', etc.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Password
    private String password;

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();
    private LocalDateTime lastLogin = LocalDateTime.now();
    private String token = UUID.randomUUID().toString();
    private boolean isActive = true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Telefono> phones;

    // Getters y Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Telefono> getPhones() {
        return phones;
    }

    public void setPhones(List<Telefono> phones) {
        this.phones = phones;
    }
}

