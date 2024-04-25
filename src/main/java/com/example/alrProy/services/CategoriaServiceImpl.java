package com.example.alrProy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alrProy.domain.Categoria;
import com.example.alrProy.repositories.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    CategoriaRepository categoriaRepositorio;

    public Categoria anhadir (Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    public List<Categoria> obtenerTodos() {
        return categoriaRepositorio.findAll();
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepositorio.findById(id).orElse(null);
    }

    public void borrar (Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElse(null);
        if (categoria != null) {
            categoriaRepositorio.delete(categoria);
        }
    }
}
