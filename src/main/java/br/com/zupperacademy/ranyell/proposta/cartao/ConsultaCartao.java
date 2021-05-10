package br.com.zupperacademy.ranyell.proposta.cartao;

import br.com.zupperacademy.ranyell.proposta.proposta.EstadoProposta;
import br.com.zupperacademy.ranyell.proposta.proposta.Proposta;
import br.com.zupperacademy.ranyell.proposta.proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ConsultaCartao {

    private final Logger logger = LoggerFactory.getLogger(ConsultaCartao.class);

    private PropostaRepository repository;
    private CartaoClient cartaoClient;

    @Autowired
    public ConsultaCartao(PropostaRepository repository, CartaoClient cartaoClient) {
        this.repository = repository;
        this.cartaoClient = cartaoClient;
    }

    @Scheduled(fixedDelayString = "${intervalo.consulta.cartao}")
    @Transactional
    public  void associaCartao() {
        List<Proposta> propostas = repository.findByCartaoIsNullAndEstadoProposta(EstadoProposta.ELEGIVEL);
        for (Proposta proposta : propostas) {
            try{
                var cartao = cartaoClient.buscaCartaoPorProposta(proposta.getId()).getBody().getId();
                proposta.setCartao(cartao);
                logger.info("Cartão = {} adicionado a proposta de id = {}", cartao, proposta.getId());
            } catch (FeignException e) {
                logger.info("Proposta = {} ainda não possui um cartão", proposta.getId());
            }
        }
    }
}
