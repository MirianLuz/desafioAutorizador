package com.caju.devback.Desafio.Autorizador.Transacoes.domain.service;

import com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output.ContaRepository;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Categoria;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Conta;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Transacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorizadorService {

    private final ContaRepository contaRepository;

    public String autorizarTransacao(Transacao transacao) {
        Conta conta = contaRepository.findById(transacao.getAccoutId())
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));

        Categoria categoria = transacao.obterCategoria();

        if (conta.verificarSaldo(categoria, transacao.getAmount())) {
            conta.debitarSaldo(categoria, transacao.getAmount());
            contaRepository.save(conta);
            return "00";
        } else if (conta.verificarSaldo(Categoria.CASH, transacao.getAmount())) {
            conta.debitarSaldo(Categoria.CASH, transacao.getAmount());
            contaRepository.save(conta);
            return "00";
        }

        return "51";
    }
}
