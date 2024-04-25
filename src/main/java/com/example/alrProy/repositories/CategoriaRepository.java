package com.example.alrProy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alrProy.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // El repositorio incorpora por defecto varios metodos, por ejemplo:
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()
    
}
