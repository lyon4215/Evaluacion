package com.example.Evaluacion.service;

import com.example.Evaluacion.model.Usuario;
import com.example.Evaluacion.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarUsuario_Correcto() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");
        usuario.setPassword("password.123");

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecución del método
        Usuario registrado = usuarioService.registrarUsuario(usuario);

        // Verificaciones
        assertNotNull(registrado);
        assertEquals("test@example.com", registrado.getEmail());
        assertNotNull(registrado.getCreated());
        assertNotNull(registrado.getModified());
        assertNotNull(registrado.getLastLogin());
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void registrarUsuario_CorreoYaRegistrado() {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setEmail("test@example.com");

        when(usuarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(usuario));

        // Ejecución y verificación de excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.registrarUsuario(usuario));
        assertEquals("El correo ya esta registrado", exception.getMessage());

        // Verificación de interacciones con el repositorio
        verify(usuarioRepository, times(1)).findByEmail("test@example.com");
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}

