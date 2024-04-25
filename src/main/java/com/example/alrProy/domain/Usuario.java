package com.example.alrProy.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")

@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    @NotEmpty
    private String nombre;

    @Email
    private String email;

    private String direccion;

    @NotEmpty
    private String contrasena;

    private Rol rol;
}
