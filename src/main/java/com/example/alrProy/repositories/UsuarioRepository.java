package com.example.alrProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alrProy.domain.Rol;
import com.example.alrProy.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // El repositorio incorpora por defecto varios metodos, por ejemplo:
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()
    public Usuario findByNombre(String nombre);
    public List<Usuario> findByRol(Rol rol);
}
