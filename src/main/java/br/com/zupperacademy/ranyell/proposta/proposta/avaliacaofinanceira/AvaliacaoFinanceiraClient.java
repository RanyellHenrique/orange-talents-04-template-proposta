package br.com.zupperacademy.ranyell.proposta.proposta.avaliacaofinanceira;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(url = "${avaliacao.financeira.url}", name = "avaliacaofinanceira")
public interface AvaliacaoFinanceiraClient {

    @PostMapping("/api/solicitacao")
    ResponseEntity<AvaliacaoFinanceiraResponse> avaliacao(@RequestBody AvaliacaoFinanceiraRequest request);
}
