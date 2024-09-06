package com.caju.devback.Desafio.Autorizador.Transacoes.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Getter
public class Conta {

    @Id
    @GeneratedValue
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "saldos", joinColumns = @JoinColumn(name = "conta_id"))
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "saldo")
    private final Map<Categoria, BigDecimal> saldos = new EnumMap<>(Categoria.class);

    public Conta() {
        for (Categoria categoria : Categoria.values()) {
            this.saldos.put(categoria, BigDecimal.ZERO);
        }
    }

    public Conta(UUID id){
        this();
        this.id = id;
    }

    public BigDecimal getSaldo(Categoria categoria) {
        return saldos.getOrDefault(categoria, BigDecimal.ZERO);
    }

    public void creditarSaldo(Categoria categoria, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor a ser creditado não pode ser negativo");
        }
        saldos.put(categoria, getSaldo(categoria).add(valor));
    }

    public void debitarSaldo(Categoria categoria, BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor a ser debitado não pode ser negativo");
        }
        BigDecimal saldoAtual = getSaldo(categoria);
        if (saldoAtual.compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente para débito");
        }
        saldos.put(categoria, (saldoAtual.subtract(valor)));
    }
}