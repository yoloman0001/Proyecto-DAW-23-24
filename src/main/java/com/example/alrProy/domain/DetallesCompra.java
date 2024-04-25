package com.example.alrProy.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

@Entity
public class DetallesCompra {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Producto producto;

    private Integer cantidad;
}
