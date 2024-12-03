package com.example.Evaluacion.service;

import com.example.Evaluacion.model.Usuario;
import com.example.Evaluacion.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya esta registrado");
        }
        usuario.setCreated(LocalDateTime.now());
        usuario.setModified(LocalDateTime.now());
        usuario.setLastLogin(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }
}
