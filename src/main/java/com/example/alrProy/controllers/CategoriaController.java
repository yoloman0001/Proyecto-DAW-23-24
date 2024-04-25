package com.example.alrProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alrProy.domain.Categoria;
import com.example.alrProy.services.CategoriaServiceImpl;
import com.example.alrProy.services.ProductoServiceImpl;

import jakarta.validation.Valid;

@Controller
public class CategoriaController {
    @Autowired
    CategoriaServiceImpl categoriaService;
    @Autowired
    ProductoServiceImpl productoService;

    @GetMapping("/categorias")
    public String showProducts(@RequestParam(required = false) Integer op, Model model) {
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        if (op != null) {
            switch (op) {
                case 1 -> model.addAttribute("msg", "Error al intentar añadir la categoría");
                case 2 -> model.addAttribute("msg", "No se puede borrar una categoria que tenga productos asignados");
            }
        }
        return "categorias/categoriasView";
    }

    @GetMapping("/categorias/nuevo")
    public String showNewProduct(Model model) {
        model.addAttribute("categoriaForm", new Categoria());
        return "categorias/newCategoriaView";
    }
    @PostMapping("/categorias/nuevo/submit")
    public String showNewProduct(@Valid Categoria categoriaForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/categorias?op=1";
        }
        categoriaService.anhadir(categoriaForm);
        return "redirect:/categorias";
    }

    @GetMapping("/categorias/borrar/{id}")
    public String showDelete(@PathVariable Long id) {
        // Si la lista de productos de esa categoría tiene algún producto, no se va a poder borrar
        if(productoService.obtenerPorCategoria(id).size() == 0){
            categoriaService.borrar(id);
            return "redirect:/categorias";
        }
        return "redirect:/categorias?op=2";
    }
}
