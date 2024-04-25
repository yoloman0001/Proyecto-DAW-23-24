package com.example.alrProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alrProy.domain.Compra;
import com.example.alrProy.services.CompraServiceImpl;
import com.example.alrProy.services.UsuarioServiceImpl;

import jakarta.validation.Valid;

@Controller
public class CompraController {
    @Autowired
    CompraServiceImpl compraService;
    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/compras")
    public String showProducts(@RequestParam(required = false) Integer op, Model model) {
        model.addAttribute("listaCompras", compraService.obtenerTodos());
        if (op != null) {
            switch (op) {
                case 1 -> model.addAttribute("msg", "Error al intentar crear la compra");
                case 2 -> model.addAttribute("msg", "Compra creada");
            }
        }
        return "compras/comprasView";
    }

    @GetMapping("/compras/nuevo")
    public String showNewProduct(Model model) {
        model.addAttribute("compraForm", new Compra());
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
        return "compras/newCompraView";
    }
    @PostMapping("/compras/nuevo/submit")
    public String showNewProduct(@Valid Compra compraForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/compras?op=1";
        }
        compraService.anhadir(compraForm);
        return "redirect:/compras?op=2";
    }

    @GetMapping("/compras/borrar/{id}")
    public String showDelete(@PathVariable Long id) {
        compraService.borrar(id);
        return "redirect:/compras";
    }
    
    @GetMapping("/compras/confirmar/{id}")
    public String confirmCompra(@PathVariable Long id) {
        Compra compraActiva = compraService.obtenerCompraActiva();
        compraService.finalizarCompra(compraActiva);
        return "redirect:/home";
    }
}
