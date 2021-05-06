package br.com.zupperacademy.ranyell.proposta.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/propostas")
public class CadastroPropostaController {

    private PropostaRepository repository;

    @Autowired
    public CadastroPropostaController(PropostaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody PropostaRequest request) {
        Proposta proposta = request.toModel();
        repository.save(proposta);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
