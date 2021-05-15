package br.com.zupperacademy.ranyell.proposta.carteira;

import br.com.zupperacademy.ranyell.proposta.cartao.CartaoClient;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SolicitaNovaCarteira {

    private CartaoClient cartaoClient;

    @Autowired
    public SolicitaNovaCarteira(CartaoClient cartaoClient) {
        this.cartaoClient = cartaoClient;
    }

    public void criaCarteira(Carteira carteira) {
        try {
            var request = new CarteiraClientRequest(carteira.getEmail(), carteira.getTipoCarteira());
            cartaoClient.criarCarteira(carteira.getNumeroCartao(), request);
        } catch (FeignException e) {
            throw  new ApiException("Não foi possível adicionar a carteira digital", HttpStatus.BAD_REQUEST);
        }
    }
}
