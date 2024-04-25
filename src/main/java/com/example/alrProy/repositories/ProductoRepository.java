package com.example.alrProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alrProy.domain.Categoria;
import com.example.alrProy.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // El repositorio incorpora por defecto varios metodos, por ejemplo:
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()
    public List<Producto> findByCategoria(Categoria categoria);
    public List<Producto> findByTemporada(String temporada);
}
