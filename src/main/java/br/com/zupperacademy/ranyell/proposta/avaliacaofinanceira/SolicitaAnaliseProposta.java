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
public class SolicitaAnaliseProposta {

    private final Logger logger = LoggerFactory.getLogger(SolicitaAnaliseProposta.class);

    private AvaliacaoFinanceiraClient avaliacaoFinanceiraClient;

    @Autowired
    public SolicitaAnaliseProposta(AvaliacaoFinanceiraClient avaliacaoFinanceiraClient) {
        this.avaliacaoFinanceiraClient = avaliacaoFinanceiraClient;
    }

    public void analiseProposta(Proposta proposta) {
        try{
            var avaliacao = avaliacaoFinanceiraClient.avaliacao(new AvaliacaoFinanceiraRequest(proposta.getDocumento(),
                                                        proposta.getNome(), proposta.getId())).getBody();
            logger.info("Avaliação financeira: {}, Proposta de id: {}", EstadoProposta.ELEGIVEL, proposta.getId());
            proposta.setEstadoProposta(avaliacao.getEstadoProposta());
        }catch (FeignException.UnprocessableEntity e) {
            logger.info("Avaliação financeira: {}, Proposta de id: {}", EstadoProposta.NAO_ELEGIVEL, proposta.getId());
            proposta.setEstadoProposta(EstadoProposta.NAO_ELEGIVEL);
        } catch (Exception e) {
            throw  new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
