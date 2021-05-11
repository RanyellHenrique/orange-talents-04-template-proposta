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

    private PropostaRepository propostaRepository;
    private CartaoClient cartaoClient;
    private CartaoRepository cartaoRepository;

    @Autowired
    public ConsultaCartao(PropostaRepository propostaRepository, CartaoClient cartaoClient, CartaoRepository cartaoRepository) {
        this.propostaRepository = propostaRepository;
        this.cartaoClient = cartaoClient;
        this.cartaoRepository = cartaoRepository;
    }

    @Scheduled(fixedDelayString = "${intervalo.consulta.cartao}")
    @Transactional
    public  void associaCartao() {
        List<Proposta> propostas = propostaRepository.findByCartaoIsNullAndEstadoProposta(EstadoProposta.ELEGIVEL);
        for (Proposta proposta : propostas) {
            try{
                var cartao = cartaoClient.buscaCartaoPorProposta(proposta.getId()).getBody();
                cartaoRepository.save(cartao.toModel(proposta));
                logger.info("Cartão = {} adicionado a proposta de id = {}", cartao.getId(), proposta.getId());
            } catch (FeignException e) {
                logger.info("Proposta = {} ainda não possui um cartão", proposta.getId());
            }
        }
    }
}
