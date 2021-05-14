package br.com.zupperacademy.ranyell.proposta.aviso;

import br.com.zupperacademy.ranyell.proposta.cartao.CartaoClient;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SolicitaAviso {

    private CartaoClient cartaoClient;

    @Autowired
    public SolicitaAviso(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public void avisaSistema(Aviso aviso){
        try {
            var avisoClientRequest = new AvisoClientRequest(aviso.getDestino(), aviso.getValidoAte());
            cartaoClient.avisar(aviso.getNumeroDoCartao(),avisoClientRequest);
        } catch (FeignException e) {
            throw new ApiException("Erro ao notificar o sistema do aviso de viagem", HttpStatus.BAD_REQUEST);
        }
    }
}
