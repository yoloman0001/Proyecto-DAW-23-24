package com.example.alrProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alrProy.domain.Compra;
import com.example.alrProy.domain.Usuario;


public interface CompraRepository extends JpaRepository<Compra, Long> {
    // El repositorio incorpora por defecto varios metodos, por ejemplo:
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()
    public List<Compra> findByUsuario(Usuario usuario);
    public Compra findByEstado(String estado);
}
