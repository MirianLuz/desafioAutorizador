package com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output;

import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransacaoRepository extends JpaRepository<Transacao, UUID> {
}
