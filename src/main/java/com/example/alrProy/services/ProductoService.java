package com.example.alrProy.services;

import java.util.List;

import com.example.alrProy.domain.Producto;

public interface ProductoService {
    public Producto anhadir (Producto producto);

    public List<Producto> obtenerTodos();

    public Producto obtenerPorId(Long id);

    public void borrar (Long id);

    public Producto editar (Producto producto);

    public List<Producto> obtenerPorCategoria (Long id);

    public List<Producto> obtenerPorTemporada (String temporada);
}
