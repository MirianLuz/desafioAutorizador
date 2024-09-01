package com.caju.devback.Desafio.Autorizador.Transacoes.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Transacao {
    private final UUID id;
    @NonNull
    private final UUID accoutId;

    @NonNull
    private final double amount;

    @NonNull
    private final String merchant;

    @NonNull
    private final String mcc;

    public Categoria obterCategoria(){
        switch (mcc){
            case "5411":
            case "5412":
                return Categoria.FOOD;
            case "5811":
            case "5812":
                return Categoria.MEAL;
            default:
                return Categoria.CASH;
        }
    }
}
