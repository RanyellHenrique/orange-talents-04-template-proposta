package br.com.zupperacademy.ranyell.proposta.bloqueio;

import br.com.zupperacademy.ranyell.proposta.cartao.CartaoClient;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SolicitaBloqueio {

    private CartaoClient cartaoClient;

    @Autowired
    public SolicitaBloqueio(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public void  bloqueiaCartao(Bloqueio bloqueio) {
        try {
            var bloqueioRequest = new BloqueioClientRequest(bloqueio.getUserAgent());
            cartaoClient.bloquear(bloqueio.getNumeroDoCartao(), bloqueioRequest).getBody();
            bloqueio.bloquearCartao();
        } catch (FeignException e ) {
            throw  new ApiException("Erro ao notificar o sistema do cartão para bloquear o cartão", HttpStatus.BAD_REQUEST);
        }
    }
}
