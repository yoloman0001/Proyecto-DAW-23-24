package com.example.alrProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.alrProy.domain.Compra;
import com.example.alrProy.domain.DetallesCompra;
import com.example.alrProy.services.CompraServiceImpl;
import com.example.alrProy.services.DetallesCompraServiceImpl;
import com.example.alrProy.services.ProductoServiceImpl;

import jakarta.validation.Valid;

@Controller
public class DetallesCompraController {
    @Autowired
    DetallesCompraServiceImpl detallesCompraService;
    @Autowired
    CompraServiceImpl compraService;
    @Autowired
    ProductoServiceImpl productoService;

    @GetMapping("/carritoCompra")
    public String showCarrito(Model model) {
        Compra compraActiva = compraService.obtenerCompraActiva();
        if (compraActiva == null) {
            return "detallesCompra/carritoVacioView";
        }
        String id = compraActiva.getId().toString();
        return "redirect:/detallesCompra/"+id;
    }

    @GetMapping("/detallesCompra/{id}")
    public String showDetallesCompra(@PathVariable Long id, Model model) {
        Compra compra = compraService.obtenerPorId(id);
        model.addAttribute("listaDetallesCompra", detallesCompraService.obtenerPorCompra(compra));
        model.addAttribute("compra", compra);
        return "detallesCompra/detallesCompraView";
    }

    @GetMapping("/detallesCompra/nuevo")
    public String showNewDetallesCompra(Model model) {
        model.addAttribute("detallesCompraForm", new DetallesCompra());
        model.addAttribute("listaCompras", compraService.obtenerTodos());
        model.addAttribute("listaProductos", productoService.obtenerTodos());
        return "detallesCompra/newDetallesCompraView";
    }
    @PostMapping("/detallesCompra/nuevo/submit")
    public String showNewDetallesCompra(@Valid DetallesCompra detallesCompra, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            detallesCompraService.anhadir(detallesCompra);
            compraService.calcularValorTotal(detallesCompra);
        }
        return "redirect:/compras";
    }
    
    @GetMapping("/detallesCompra/borrar/{id}")
    public String showDelete(@PathVariable Long id) {
        detallesCompraService.borrar(id);
        return "redirect:/compras";
    }
    
}
