package com.caju.devback.Desafio.Autorizador.Transacoes.configuracao;

import com.caju.devback.Desafio.Autorizador.Transacoes.adapter.output.ContaRepository;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Categoria;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Conta;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.UUID;

@Configuration
public class DatabaseLoader {

    @Bean
    CommandLineRunner initDatabase(ContaRepository contaRepository) {
        return args -> {
            Conta conta = new Conta(UUID.fromString("ac9508d3-e9a8-4649-a7ab-a83b7b35452e"));
            conta.creditarSaldo(Categoria.FOOD, new BigDecimal("500.00"));
            conta.creditarSaldo(Categoria.MEAL, new BigDecimal("300.00"));
            conta.creditarSaldo(Categoria.CASH, new BigDecimal("1000.00"));

            contaRepository.save(conta);
        };
    }
}
