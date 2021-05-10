package br.com.zupperacademy.ranyell.proposta.avaliacaofinanceira;

import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import br.com.zupperacademy.ranyell.proposta.proposta.EstadoProposta;
import br.com.zupperacademy.ranyell.proposta.proposta.Proposta;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AvaliaProposta {

    private final Logger logger = LoggerFactory.getLogger(AvaliaProposta.class);

    private AvaliacaoFinanceiraClient avaliacaoFinanceiraClient;

    @Autowired
    public AvaliaProposta(AvaliacaoFinanceiraClient avaliacaoFinanceiraClient) {
        this.avaliacaoFinanceiraClient = avaliacaoFinanceiraClient;
    }

    public EstadoProposta analiseProposta(Proposta proposta) {
        try{
            avaliacaoFinanceiraClient.avaliacao(new AvaliacaoFinanceiraRequest(proposta.getDocumento(), proposta.getNome(), proposta.getId()));
            logger.info("Avaliação financeira: {}, Proposta de id: {}", EstadoProposta.ELEGIVEL, proposta.getId());
            return EstadoProposta.ELEGIVEL;
        }catch (FeignException.UnprocessableEntity e) {
            logger.info("Avaliação financeira: {}, Proposta de id: {}", EstadoProposta.NAO_ELEGIVEL, proposta.getId());
            return EstadoProposta.NAO_ELEGIVEL;
        }catch (FeignException e) {
            throw  new ApiException(e.getMessage(), HttpStatus.valueOf(e.status()));
        }
    }
}
