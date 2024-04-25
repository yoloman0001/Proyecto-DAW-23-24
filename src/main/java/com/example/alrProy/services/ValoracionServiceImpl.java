package com.example.alrProy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alrProy.domain.Producto;
import com.example.alrProy.domain.Usuario;
import com.example.alrProy.domain.Valoracion;
import com.example.alrProy.repositories.ValoracionRepository;

@Service
public class ValoracionServiceImpl {
    @Autowired
    ValoracionRepository valoracionRepositorio;

    public Valoracion anhadir (Valoracion valoracion) {
        return valoracionRepositorio.save(valoracion);
    }

    public List<Valoracion> obtenerTodos () {
        return valoracionRepositorio.findAll();
    }

    public Valoracion obtenerPorId (Long id) {
        return valoracionRepositorio.findById(id).orElse(null);
    }

    public void borrar (Long id) {
        Valoracion valoracion = valoracionRepositorio.findById(id).orElse(null);
        if (valoracion != null) {
            valoracionRepositorio.delete(valoracion);
        }
    }

    public List<Valoracion> obtenerPorUsuario (Usuario usuario) {
        return valoracionRepositorio.findByUsuario(usuario);
    }

    public List<Valoracion> obtenerPorProducto (Producto producto) {
        return valoracionRepositorio.findByProducto(producto);
    }
}
