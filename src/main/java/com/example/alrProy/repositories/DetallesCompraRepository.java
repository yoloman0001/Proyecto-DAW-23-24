package com.example.alrProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alrProy.domain.Compra;
import com.example.alrProy.domain.DetallesCompra;
import com.example.alrProy.domain.Producto;


public interface DetallesCompraRepository extends JpaRepository<DetallesCompra, Long> {
    // El repositorio incorpora por defecto varios metodos, por ejemplo:
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()

    // Este metodo devuelve todos los productos de una compra concreta
    public List<DetallesCompra> findByCompra(Compra compra);
    // Este metodo devuelve todas las compras donde aparezca un producto concreto
    public List<DetallesCompra> findByProducto(Producto producto);
}
