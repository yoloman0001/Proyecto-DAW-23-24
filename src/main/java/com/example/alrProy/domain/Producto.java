package com.example.alrProy.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")

@Entity
public class Producto {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String nombre;

    @NotNull
    private Double precio;

    @NotEmpty
    private String iva;

    @NotEmpty
    private String temporada;

    private Boolean esDestacado;

    private Integer stock;

    @ToString.Exclude
    @ManyToOne
    private Categoria categoria;
}
