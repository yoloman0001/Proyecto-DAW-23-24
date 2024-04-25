package com.example.alrProy.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class MainService {
    public Integer fechaHome(){
        int anho = LocalDate.now().getYear();
        return anho;
    }
    public String nombreAboutUs(){
        String nombre= "Antón Lago Rodríguez";
        return nombre;
    }
}
