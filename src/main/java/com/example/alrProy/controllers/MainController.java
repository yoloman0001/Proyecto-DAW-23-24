package com.example.alrProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.alrProy.dto.UsuarioDto;
import com.example.alrProy.services.CompraServiceImpl;
import com.example.alrProy.services.MainService;
import com.example.alrProy.services.UsuarioServiceImpl;


@Controller
public class MainController {
    @Autowired
    MainService mainServicio;
    @Autowired
    UsuarioServiceImpl usuarioService;
    @Autowired
    CompraServiceImpl compraService;

    @GetMapping({"/", "/home"})
    public String showHome(@RequestParam(required = false) Integer op, Model model) {
        int fecha = mainServicio.fechaHome();
        model.addAttribute("fecha", fecha);
        if (op != null) {
            switch (op) {
                case 1 -> model.addAttribute("msg", "Error en el registro de usuario: credenciales incorrectas");
                case 2 -> model.addAttribute("msg", "Usuario registrado correctamente. Inicia sesión");
                case 3 -> model.addAttribute("msg", "Contraseña cambiada correctamente.");
            }
        }
        // model.addAttribute("compraActiva", compraService.obtenerCompraActiva());
        return "indexView";
    }
    @GetMapping("/aboutUs")
    public String showQuienesSomos(Model model) {
        String nombre = mainServicio.nombreAboutUs();
        model.addAttribute("nombre", nombre);
        return "aboutUsView";
    }
    @GetMapping("/contact")
    public String showContactos() {
        return "contactView";
    }

    @GetMapping("/privacy")
    public String showPrivacyPolicy() {
        return "footerpages/privacidadView";
    }

    @GetMapping("/cookies")
    public String showCookiePolicy() {
        return "footerpages/cookiesView";
    }

    @GetMapping("/credits")
    public String showCredits() {
        return "creditosView";
    }

    @GetMapping("/password")
    public String showEditPassword(Model model) {
        model.addAttribute("passwordForm", new UsuarioDto());
        return "usuarios/editPasswordView";
    }
    @PostMapping("/password/submit")
    public String showEditPasswordSubmit(UsuarioDto usuarioDto) {
        usuarioService.cambiarPassword(usuarioDto);
        return "redirect:/home?op=3";
    }

    @GetMapping("/accessError")
    public String showAccessError() {
        return "error/403";
    }

}
