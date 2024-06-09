package com.example.alrProy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alrProy.domain.Categoria;
import com.example.alrProy.domain.Producto;
import com.example.alrProy.repositories.CategoriaRepository;
import com.example.alrProy.repositories.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepositorio;
    @Autowired
    CategoriaRepository categoriaRepositorio;

    public Producto anhadir (Producto producto) {
        if (producto.getEsDestacado() == null) {
            producto.setEsDestacado(false);
        }
        return productoRepositorio.save(producto);
    }

    public List<Producto> obtenerTodos() {
        return productoRepositorio.findAll();
    }

    public Producto obtenerPorId(Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    public void borrar (Long id) {
        Producto producto = productoRepositorio.findById(id).orElse(null);
        if (producto != null) {
            productoRepositorio.delete(producto);
        }
    }

    public Producto editar (Producto producto) {
        // Al definir el Equals() y el HashCode() en la entidad,
        // podemos usar el metodo save() para editar un producto ya presente
        return productoRepositorio.save(producto);
    }

    public List<Producto> obtenerPorCategoria(Long id) {
        Categoria categoria = categoriaRepositorio.findById(id).orElse(null);
        if (categoria != null) {
            return productoRepositorio.findByCategoria(categoria);
        }
        return null;
    }

    public List<Producto> obtenerPorTemporada(String temporada) {
        if (temporada != "") {
            return productoRepositorio.findByTemporada(temporada);
        }
        return null;
    }
    public List<String> obtenerTodasTemporadas() {
        ArrayList<String> listaTemporadas = new ArrayList<>();
        List<Producto> productos = productoRepositorio.findAll();
        for (int i=0; i < productos.size(); i++) {
            if (!listaTemporadas.contains(productos.get(i).getTemporada())) {
                listaTemporadas.add(productos.get(i).getTemporada());
            }
        }
        return listaTemporadas;
    }

    public List<Producto> obtenerDestacados() {
        ArrayList<Producto> destacados = new ArrayList<>();
        List<Producto> productos = productoRepositorio.findAll();
        for (int i=0; i < productos.size(); i++) {
            if (productos.get(i).getEsDestacado()) {
                destacados.add(productos.get(i));
            }
        }
        return destacados;
    }

    public void reducirStock (Producto producto) {
        producto.setStock(producto.getStock()-1);
        productoRepositorio.save(producto);
    }
}
