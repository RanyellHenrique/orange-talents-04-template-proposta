package br.com.zupperacademy.ranyell.proposta.biometria;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/cartoes")
public class CadastroBiometriaController {

    private CartaoRepository cartaoRepository;
    private BiometriaRepository biometriaRepository;

    @Autowired
    public CadastroBiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
    }

    @PostMapping("/{id}/biometrias")
    @Transactional
    public ResponseEntity<Void> insert(@RequestBody @Valid BiometriaRequest request, @PathVariable Long id) {
        Optional<Cartao> supostoCartao = cartaoRepository.findById(id);

        if(supostoCartao.isEmpty()) {
            throw new ApiException("Cartão não encontrado", HttpStatus.NOT_FOUND);
        }
        Biometria biometria = request.toModel(supostoCartao.get());
        biometriaRepository.save(biometria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
