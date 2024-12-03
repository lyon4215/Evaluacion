package com.example.Evaluacion.controller;

import com.example.Evaluacion.model.Usuario;
import com.example.Evaluacion.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        // Manejo de errores de validación
        if (bindingResult.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Error de validación", "detalles", errores));
        }

        try {
            // Registro del usuario
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Usuario registrado exitosamente",
                    "usuario", nuevoUsuario
            ));
        } catch (RuntimeException e) {
            // Manejo de errores de negocio
            return ResponseEntity.badRequest().body(Map.of("mensaje", e.getMessage()));
        }
    }
}
