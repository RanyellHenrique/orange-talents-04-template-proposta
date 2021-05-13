package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.bloqueio.BloqueioRequest;
import br.com.zupperacademy.ranyell.proposta.bloqueio.BloqueioResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${api.cartao.url}", name = "${api.cartao.name}")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    public ResponseEntity<CartaoResponse> buscaCartaoPorProposta(@PathVariable("idProposta") Long idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    public ResponseEntity<BloqueioResponse> bloquearCartao(@PathVariable String id, @RequestBody BloqueioRequest request);
}
