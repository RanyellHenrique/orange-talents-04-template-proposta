package br.com.zupperacademy.ranyell.proposta.bloqueio;

import br.com.zupperacademy.ranyell.proposta.cartao.CartaoClient;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class NotificaSistema {

    private CartaoClient cartaoClient;

    @Autowired
    public NotificaSistema(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public void  bloqueiaCartao(Bloqueio bloqueio) {
        try {
            var bloqueioRequest = new BloqueioRequest(bloqueio.getUserAgent());
            cartaoClient.bloquearCartao(bloqueio.getCartao().getNumero(), bloqueioRequest).getBody();
            bloqueio.getCartao().bloquearCartao();
        } catch (FeignException e ) {
            throw  new ApiException("Erro ao notificar o sistema do cartão para bloquear o cartão", HttpStatus.BAD_REQUEST);
        }
    }
}
