package com.caju.devback.Desafio.Autorizador.Transacoes.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Saldo {
    @NonNull
    private final Categoria categoria;

    @NonNull
    private double valor;

    public void debitar(double valor){
        if (valor > 0 && this.valor >= valor){
            this.valor -= valor;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente ou valor inválido");
        }
    }

    public void creditar(double valor){
        if (valor > 0){
            this.valor += valor;
        } else {
            throw new IllegalArgumentException("Valor inválido");
        }
    }
}
