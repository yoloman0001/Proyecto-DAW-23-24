package com.example.alrProy.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alrProy.domain.Compra;
import com.example.alrProy.domain.DetallesCompra;
import com.example.alrProy.domain.Producto;
import com.example.alrProy.domain.Usuario;
import com.example.alrProy.repositories.DetallesCompraRepository;

@Service
public class DetallesCompraServiceImpl {
    @Autowired
    DetallesCompraRepository detallesCompraRepositorio;
    @Autowired
    CompraServiceImpl compraService;
    @Autowired
    UsuarioServiceImpl usuarioService;

    public DetallesCompra anhadir (DetallesCompra detallesCompra) {
        return detallesCompraRepositorio.save(detallesCompra);
    }

    public DetallesCompra obtenerPorId (Long id) {
        return detallesCompraRepositorio.findById(id).orElse(null);
    }

    public void borrar (Long id) {
        DetallesCompra detallesCompra = detallesCompraRepositorio.findById(id).orElse(null);
        if (detallesCompra != null) {
            Double valorTotal = detallesCompra.getCompra().getValorTotal();
            valorTotal -= detallesCompra.getProducto().getPrecio();
            valorTotal = (double) Math.round(valorTotal * 100) / 100;
            detallesCompra.getCompra().setValorTotal(valorTotal);
            detallesCompraRepositorio.delete(detallesCompra);
            detallesCompra.getProducto().setStock(detallesCompra.getProducto().getStock() + 1);
        }
    }

    public List<DetallesCompra> obtenerPorCompra (Compra compra) {
        return detallesCompraRepositorio.findByCompra(compra);
    }

    public List<DetallesCompra> obtenerPorProducto (Producto producto) {
        return detallesCompraRepositorio.findByProducto(producto);
    }

    public void anhadirACompra (Producto producto) {
        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        Compra compraActiva = compraService.obtenerCompraActiva();
        // Si no hay ninguna compra activa, se crea una nueva
        if (compraActiva == null) {
            Compra nuevaCompra = new Compra(0L, LocalDate.now(), "EN_PROCESO", 0.0D, usuarioConectado);
            compraService.anhadir(nuevaCompra);
            // Antes de añadir el producto, compruebo que lo que va a comprar tienen stock
            if (producto.getStock() == 0) {
                throw new RuntimeException("Producto no está en stock");
            }
            DetallesCompra detallesCompra = new DetallesCompra(0L, nuevaCompra, producto, 1);
            detallesCompraRepositorio.save(detallesCompra);
            compraService.calcularValorTotal(detallesCompra);
        // Si ya hay una compra activa, compruebo que el usuario que hizo esa compra sea el usuario conectado
        } else if (compraActiva.getUsuario().getNombre().equals(usuarioConectado.getNombre())) {
            // Antes de añadir el producto, compruebo que lo que va a comprar tienen stock
            if (producto.getStock() == 0) {
                throw new RuntimeException("Producto no está en stock");
            }
            DetallesCompra detallesCompra = new DetallesCompra(0L, compraActiva, producto, 1);
            detallesCompraRepositorio.save(detallesCompra);
            compraService.calcularValorTotal(detallesCompra);
        } else {
            throw new RuntimeException("Error al intentar comprar el producto");
        }
    }
}
