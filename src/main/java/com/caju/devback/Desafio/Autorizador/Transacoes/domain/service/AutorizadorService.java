package com.caju.devback.Desafio.Autorizador.Transacoes.domain.service;

import com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output.ContaRepository;
import com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output.TransacaoRepository;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Categoria;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Conta;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Transacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AutorizadorService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    @Autowired
    public AutorizadorService(ContaRepository contaRepository, TransacaoRepository transacaoRepository) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public String autorizarTransacao(Transacao transacao) {
        Conta conta = contaRepository.findById(transacao.getAccountId())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        Categoria categoria = determinarCategoriaPorMcc(transacao.getMcc());

        try {
            conta.debitarSaldo(categoria, transacao.getAmount());
            contaRepository.save(conta);

            transacaoRepository.save(transacao);

            return "00";  // Código de sucesso

        } catch (RuntimeException e) {
            return "51";  // Código de saldo insuficiente
        }
    }

    private Categoria determinarCategoriaPorMcc(String mcc) {
        switch (mcc) {
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