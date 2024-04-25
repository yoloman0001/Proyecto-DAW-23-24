package com.example.alrProy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.alrProy.domain.Usuario;
import com.example.alrProy.dto.UsuarioDto;
import com.example.alrProy.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl {
    @Autowired
    UsuarioRepository usuarioRepositorio;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario anhadir (Usuario usuario) {
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        try {
            return usuarioRepositorio.save(usuario);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }

    public void borrar (Long id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        if (usuario != null) {
            usuarioRepositorio.delete(usuario);
        }
    }

    public Usuario obtenerUsuarioConectado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String nombreUsuarioConectado = authentication.getName();
            return usuarioRepositorio.findByNombre(nombreUsuarioConectado);
        }
        return null;
    }

    public Usuario cambiarPassword (UsuarioDto usuarioDto) {
        Usuario usuario = obtenerUsuarioConectado();
        usuario.setContrasena(passwordEncoder.encode(usuarioDto.getContrasena()));
        return usuarioRepositorio.save(usuario);
    }
}
