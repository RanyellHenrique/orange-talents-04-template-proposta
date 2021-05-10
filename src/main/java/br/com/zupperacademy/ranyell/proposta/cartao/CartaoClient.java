package br.com.zupperacademy.ranyell.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${api.cartao.url}", name = "${api.cartao.name}")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    public ResponseEntity<CartaoResponse> buscaCartaoPorProposta(@PathVariable("idProposta") Long idProposta);
}
