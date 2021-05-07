package br.com.zupperacademy.ranyell.proposta.proposta;

import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import br.com.zupperacademy.ranyell.proposta.proposta.avaliacaofinanceira.AvaliaProposta;
import br.com.zupperacademy.ranyell.proposta.proposta.avaliacaofinanceira.AvaliacaoFinanceiraClient;
import br.com.zupperacademy.ranyell.proposta.proposta.avaliacaofinanceira.AvaliacaoFinanceiraRequest;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class CadastroPropostaController {

    private final Logger logger = LoggerFactory.getLogger(CadastroPropostaController.class);
    private PropostaRepository repository;
    private AvaliaProposta avaliaProposta;

    @Autowired
    public CadastroPropostaController(PropostaRepository repository, AvaliaProposta avaliaProposta) {
        this.repository = repository;
        this.avaliaProposta = avaliaProposta;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> insert(@Valid @RequestBody PropostaRequest request) {
        var supostaProposta = repository.findByDocumento(request.getDocumento());

        if(supostaProposta.isPresent()) {
            throw new ApiException("Solicitante já requisitou uma proposta", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Proposta proposta = request.toModel();
        repository.save(proposta);
        proposta.setEstadoProposta(avaliaProposta.analiseProposta(proposta));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(proposta.getId()).toUri();
        logger.info("Proposta documento={} salário={} criada com sucesso! ",proposta.getDocumento(), proposta.getSalario());
        return ResponseEntity.created(uri).build();
    }



}
