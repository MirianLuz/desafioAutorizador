package com.caju.devback.Desafio.Autorizador.Transacoes.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transacao")
@Data
@Builder
public class Transacao {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull(message = "Account ID cannot be null")
    private UUID accountId;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "MCC cannot be blank")
    private String mcc;

    @NotBlank(message = "Merchant cannot be blank")
    private String merchant;
}