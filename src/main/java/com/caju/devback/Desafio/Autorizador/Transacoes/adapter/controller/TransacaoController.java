package com.caju.devback.Desafio.Autorizador.Transacoes.adapter.controller;

import com.caju.devback.Desafio.Autorizador.Transacoes.domain.Transacao;
import com.caju.devback.Desafio.Autorizador.Transacoes.domain.service.AutorizadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {

    private final AutorizadorService autorizadorService;

    @PostMapping
    public ResponseEntity<?> autorizarTransacao(@RequestBody Transacao transacao) {
        try {
            String codigoResposta = autorizadorService.autorizarTransacao(transacao);
            return ResponseEntity.ok().body("{ \"code\": \"" + codigoResposta + "\" }");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok().body("{ \"code\": \"07\" }");
        }
    }
}
