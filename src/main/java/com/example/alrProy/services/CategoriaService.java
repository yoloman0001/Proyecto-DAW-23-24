package com.example.alrProy.services;

import java.util.List;

import com.example.alrProy.domain.Categoria;

public interface CategoriaService {
    public Categoria anhadir (Categoria categoria);

    public List<Categoria> obtenerTodos();

    public Categoria obtenerPorId(Long id);

    public void borrar (Long id);
}
