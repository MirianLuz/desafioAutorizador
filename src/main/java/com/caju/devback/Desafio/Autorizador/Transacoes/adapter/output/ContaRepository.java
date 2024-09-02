package com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output;

import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<Conta, UUID> {
    Optional<Conta> findByAccountNumber(String accountNumber);
}
