package com.example.alrProy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.alrProy.domain.Producto;
import com.example.alrProy.domain.Usuario;
import com.example.alrProy.domain.Valoracion;


public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {
    // El repositorio incorpora por defecto varios metodos, por ejemplo:
    // findAll()
    // findById()
    // save()
    // delete()
    // deleteById()

    // Este metodo devuelve todos los productos de una compra concreta
    public List<Valoracion> findByUsuario(Usuario usuario);
    // Este metodo devuelve todas las compras donde aparezca un producto concreto
    public List<Valoracion> findByProducto(Producto producto);
}
