package com.example.alrProy.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")

@Entity
public class Compra {
    @Id
    @GeneratedValue
    private Long id;

    @PastOrPresent
    private LocalDate fecha;

    private String estado;

    private Double valorTotal;

    @ManyToOne
    private Usuario usuario;

    public void sumarValor (Double importe) {
        this.valorTotal += importe;
    }
}
