package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.aviso.AvisoClientRequest;
import br.com.zupperacademy.ranyell.proposta.aviso.AvisoClientResponse;
import br.com.zupperacademy.ranyell.proposta.bloqueio.BloqueioClientRequest;
import br.com.zupperacademy.ranyell.proposta.bloqueio.BloqueioClientResponse;
import br.com.zupperacademy.ranyell.proposta.carteira.CarteiraClientRequest;
import br.com.zupperacademy.ranyell.proposta.carteira.CarteiraClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${api.cartao.url}", name = "${api.cartao.name}")
public interface CartaoClient {

    @GetMapping("/api/cartoes")
    public ResponseEntity<CartaoClientResponse> buscaCartaoPorProposta(@PathVariable("idProposta") Long idProposta);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    public ResponseEntity<BloqueioClientResponse> bloquear(@PathVariable String id, @RequestBody BloqueioClientRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    public ResponseEntity<AvisoClientResponse> avisar(@PathVariable String id, @RequestBody AvisoClientRequest request);

    @PostMapping("/api/cartoes/{id}/carteiras")
    public  ResponseEntity<CarteiraClientResponse> criarCarteira(@PathVariable String id, @RequestBody CarteiraClientRequest request);
}
