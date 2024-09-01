package com.caju.devback.Desafio.Autorizador.Transacoes.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Conta {
    private final UUID id;

    @NonNull
    private final Map<Categoria, Saldo> saldos;

    public boolean debitarSaldo(Categoria categoria, double valor){
        Saldo saldo = saldos.get(categoria);
        if (saldo != null && saldo.getValor() >= valor){
            saldo.debitar(valor);
            return true;
        }
        return false;
    }

    public boolean verificarSaldo(Categoria categoria, double valor){
        Saldo saldo = saldos.get(categoria);
        return saldo != null && saldo.getValor() >= valor;
    }

}
