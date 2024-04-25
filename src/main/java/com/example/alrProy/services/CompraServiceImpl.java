package com.example.alrProy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alrProy.domain.Compra;
import com.example.alrProy.domain.DetallesCompra;
import com.example.alrProy.domain.Usuario;
import com.example.alrProy.repositories.CompraRepository;
import com.example.alrProy.repositories.UsuarioRepository;

@Service
public class CompraServiceImpl {
    @Autowired
    CompraRepository compraRepositorio;
    @Autowired
    UsuarioRepository usuarioRepositorio;
    @Autowired
    UsuarioServiceImpl usuarioServicio;

    public Compra anhadir (Compra compra) {
        compra.setEstado("EN_PROCESO");
        compra.setValorTotal(0.0);
        compra.setUsuario(usuarioServicio.obtenerUsuarioConectado());
        return compraRepositorio.save(compra);
    }

    public List<Compra> obtenerTodos() {
        return compraRepositorio.findAll();
    }

    public Compra obtenerPorId(Long id) {
        return compraRepositorio.findById(id).orElse(null);
    }

    public Compra obtenerCompraActiva() {
        return compraRepositorio.findByEstado("EN_PROCESO");
    }

    public void borrar (Long id) {
        Compra compra = compraRepositorio.findById(id).orElse(null);
        if (compra != null) {
            compraRepositorio.delete(compra);
        }
    }

    public List<Compra> obtenerPorUsuario(Long id) {
        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);
        if (usuario != null) {
            return compraRepositorio.findByUsuario(usuario);
        }
        return null;
    }

    public void calcularValorTotal (DetallesCompra detallesCompra) {
        Compra compra = detallesCompra.getCompra();
        Double importe = detallesCompra.getProducto().getPrecio() * detallesCompra.getCantidad();
        compra.sumarValor(importe);
        compraRepositorio.save(compra);
    }

    public Compra finalizarCompra (Compra compra) {
        compra.setEstado("FINALIZADA");
        return compraRepositorio.save(compra);
    }
}
