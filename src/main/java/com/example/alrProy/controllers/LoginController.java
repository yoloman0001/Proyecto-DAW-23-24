package com.example.alrProy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.alrProy.domain.Rol;
import com.example.alrProy.domain.Usuario;
import com.example.alrProy.services.UsuarioServiceImpl;

import jakarta.validation.Valid;


@Controller
public class LoginController {
    @Autowired
    UsuarioServiceImpl usuarioService;

    @GetMapping("/signin")
    public String showLogin() {
        return "logins/loginView";
    }

    @GetMapping("/signout")
    public String showLogout() {
        return "logins/logoutView";
    }

    @GetMapping("/registrarse")
    public String registerNewUser(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "logins/registroView";
    }

    @PostMapping("/registrarse/submit")
    public String registerNewUserSubmit(@Valid Usuario usuarioForm, BindingResult bindingResult) {
        usuarioForm.setRol(Rol.USUARIO);
        if (bindingResult.hasErrors())
            return "redirect:/home?op=1";
        usuarioService.anhadir(usuarioForm);
        return "redirect:/home?op=2";
    }
    
}
