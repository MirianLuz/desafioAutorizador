package com.caju.devback.Desafio.Autorizador.Transacoes.domain;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
public class Saldo {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Conta conta;
    private final Categoria categoria;

    private double valor;

    public void debitar(double valor) {
        if (valor > 0 && this.valor >= valor) {
            this.valor -= valor;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente ou valor inválido.");
        }
    }

    public void creditar(double valor) {
        if (valor > 0) {
            this.valor += valor;
        } else {
            throw new IllegalArgumentException("Valor inválido.");
        }
    }
}