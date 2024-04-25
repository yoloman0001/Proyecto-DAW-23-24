package com.example.alrProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alrProy.domain.Usuario;
import com.example.alrProy.services.UsuarioServiceImpl;

import jakarta.validation.Valid;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/usuarios")
    public String showUsers(@RequestParam(required = false) Integer op, Model model) {
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
        if (op != null) {
            switch (op) {
                case 1 -> model.addAttribute("msg", "Error al intentar añadir el usuario");
                case 2 -> model.addAttribute("msg", "Usuario añadido correctamente");
                case 3 -> model.addAttribute("msg", "Datos del usuario actualizados");
                case 4 -> model.addAttribute("msg", "No se puede borrar un usuario que está conectado");
            }
        }
        return "usuarios/usuariosView";
    }

    @GetMapping("/usuarios/nuevo")
    public String showNewUser(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "usuarios/newUserView";
    }
    @PostMapping("/usuarios/nuevo/submit")
    public String showNewUser(@Valid Usuario usuarioForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/usuarios?op=1";
        }
        usuarioService.anhadir(usuarioForm);
        return "redirect:/usuarios?op=2";
    }

    @GetMapping("/usuarios/borrar/{id}")
    public String showDelete(@PathVariable Long id) {
        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        if (usuarioConectado == usuarioService.obtenerPorId(id) ) {
            return "redirect:/usuarios?op=4";
        }
        usuarioService.borrar(id);
        return "redirect:/usuarios";
    }
    
}
