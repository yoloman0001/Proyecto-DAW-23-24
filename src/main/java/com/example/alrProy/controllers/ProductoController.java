package com.example.alrProy.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alrProy.domain.Categoria;
import com.example.alrProy.domain.Producto;
import com.example.alrProy.services.CategoriaServiceImpl;
import com.example.alrProy.services.DetallesCompraServiceImpl;
import com.example.alrProy.services.ProductoServiceImpl;
import jakarta.validation.Valid;

@Controller
public class ProductoController {
    @Autowired
    ProductoServiceImpl productoService;
    @Autowired
    CategoriaServiceImpl categoriaService;
    @Autowired
    DetallesCompraServiceImpl detallesCompraService;

    @GetMapping("/productos")
    public String showProducts(@RequestParam(required = false) Integer op, Model model) {
        model.addAttribute("listaProductos", productoService.obtenerTodos());
        // Para los desplegables
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        model.addAttribute("catSeleccionada", new Categoria (0L,"Todas"));
        model.addAttribute("listaTemporadas", productoService.obtenerTodasTemporadas());
        if (op != null) {
            switch (op) {
                case 1 -> model.addAttribute("msg", "Error al intentar añadir el producto");
                case 2 -> model.addAttribute("msg", "Producto añadido correctamente");
                case 3 -> model.addAttribute("msg", "Datos del producto actualizados");
                case 4 -> model.addAttribute("msg", "Producto añadido al carrito");
                case 5 -> model.addAttribute("msg", "Error al intentar comprar el producto");
            }
        }
        return "productos/productosView";
    }

    @GetMapping("/productos/nuevo")
    public String showNewProduct(Model model) {
        model.addAttribute("productoForm", new Producto());
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "productos/newProductView";
    }
    
    @PostMapping("/productos/nuevo/submit")
    public String showNewProduct(@Valid Producto productoForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/productos?op=1";
        }
        productoService.anhadir(productoForm);
        return "redirect:/productos?op=2";
    }

    @GetMapping("/productos/borrar/{id}")
    public String showDelete(@PathVariable Long id) {
        productoService.borrar(id);
        return "redirect:/productos";
    }

    @GetMapping("/productos/editar/{id}")
    public String showEdit(@PathVariable Long id, Model model) {
        Producto productoForm = productoService.obtenerPorId(id);
        model.addAttribute("productoForm", productoForm);
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        return "productos/editProductoView";
    }
    @PostMapping("/productos/editar/submit")
    public String showEditSubmit(Producto productoForm) {
        productoService.editar(productoForm);
        return "redirect:/productos?op=3";
    }

    @GetMapping("/productos/porCat/{idCat}")
    public String showListInCategoria(@PathVariable Long idCat, Model model) {
        model.addAttribute("listaProductos", productoService.obtenerPorCategoria(idCat));
        model.addAttribute("listaCategorias", categoriaService.obtenerTodos());
        model.addAttribute("catSeleccionada", categoriaService.obtenerPorId(idCat));
        return "productos/productosView";
    }

    @GetMapping("/productos/porTem/{temporada}")
    public String showListInCategoria(@PathVariable String temporada, Model model) {
        model.addAttribute("listaProductos", productoService.obtenerPorTemporada(temporada));
        model.addAttribute("listaTemporadas", productoService.obtenerTodasTemporadas());
        model.addAttribute("temSeleccionada", productoService.obtenerPorTemporada(temporada));
        return "productos/productosView";
    }

    @GetMapping("/productos/buscar/{texto}")
    public String searchProductos(@PathVariable String texto, Model model) {
        for (int i=0; i<productoService.obtenerTodos().size(); i++) {
            ArrayList<Producto> listaProductos = new ArrayList<>(productoService.obtenerTodos());
            if (listaProductos.get(i).getNombre().contains(texto)) {
                return "redirect:/productos/porCat/"+listaProductos.get(i).getCategoria().getId();
            }
        }
        return "productos/productosView";
    }

    @GetMapping("/productos/destacados")
    public String showDestacados(Model model) {
        model.addAttribute("listaProductos", productoService.obtenerDestacados());
        return "productos/destacadosView";
    }

    @GetMapping("/productos/comprar/{id}")
    public String addToCompra(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        try {
            detallesCompraService.anhadirACompra(producto);
            productoService.reducirStock(producto);
        } catch (RuntimeException ex) {
            return "redirect:/productos?op=5";
        }
        return "redirect:/productos?op=4";
    }
}
