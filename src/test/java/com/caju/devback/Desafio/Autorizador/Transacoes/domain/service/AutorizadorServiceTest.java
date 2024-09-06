package com.caju.devback.Desafio.Autorizador.Transacoes.domain.service;

import com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output.ContaRepository;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Categoria;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Conta;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AutorizadorServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private AutorizadorService autorizadorService;

    private Transacao transacao;
    private Conta conta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        transacao = Transacao.builder()
                .accountId(UUID.randomUUID())
                .amount(new BigDecimal("100.00"))
                .mcc("5811")
                .merchant("PADARIA DO ZE SAO PAULO BR")
                .build();

        conta = new Conta(UUID.randomUUID());
        conta.creditarSaldo(Categoria.MEAL, new BigDecimal("200.00"));

        when(contaRepository.findById(transacao.getAccountId())).thenReturn(Optional.of(conta));
    }

    @Test
    void testAutorizarTransacaoSaldoSuficiente() {
        String codigoResposta = autorizadorService.autorizarTransacao(transacao);
        assertEquals("00", codigoResposta);
    }

    @Test
    void testAutorizarTransacaoSaldoInsuficiente() {
        conta.debitarSaldo(Categoria.MEAL, new BigDecimal("150.00"));
        String codigoResposta = autorizadorService.autorizarTransacao(transacao);
        assertEquals("51", codigoResposta);
    }

    @Test
    void testAutorizarTransacaoUtilizandoSaldoCASH() {
        conta.debitarSaldo(Categoria.MEAL, new BigDecimal("200.00"));
        conta.creditarSaldo(Categoria.CASH, new BigDecimal("300.00"));
        String codigoResposta = autorizadorService.autorizarTransacao(transacao);
        assertEquals("00", codigoResposta);
    }

    @Test
    void testContaNaoEncontrada() {
        when(contaRepository.findById(transacao.getAccountId())).thenReturn(Optional.empty());
        String codigoResposta = autorizadorService.autorizarTransacao(transacao);
        assertEquals("07", codigoResposta);
    }

    @Test
    void testTransacaoInvalida() {

        Transacao transacaoInvalida = Transacao.builder()
                .accountId(UUID.randomUUID())
                .amount(new BigDecimal("-100.00"))
                .mcc("5811")
                .merchant("PADARIA DO ZE SAO PAULO BR")
                .build();

        String codigoResposta = autorizadorService.autorizarTransacao(transacaoInvalida);
        assertEquals("07", codigoResposta);
    }
}