package br.com.zupperacademy.ranyell.proposta.proposta;

import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class DetalhesPropostaController {

    private final Logger logger = LoggerFactory.getLogger(DetalhesPropostaController.class);

    private PropostaRepository repository;

    @Autowired
    public DetalhesPropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropostaResponse> findById(@PathVariable Long id) {
        Optional<Proposta> supostaProposta = repository.findById(id);
        if(supostaProposta.isEmpty()) {
            logger.info("Proposta de id = {} não existe", id);
            throw new ApiException("Proposta não existe", HttpStatus.NOT_FOUND);
        }
        PropostaResponse proposta = new PropostaResponse(supostaProposta.get());
        logger.info("Proposta de id = {} requisitada", id);
        return ResponseEntity.ok(proposta);
    }
}
