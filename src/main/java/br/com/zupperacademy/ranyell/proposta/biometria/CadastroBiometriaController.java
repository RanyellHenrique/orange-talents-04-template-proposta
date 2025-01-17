package br.com.zupperacademy.ranyell.proposta.biometria;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/cartoes")
public class CadastroBiometriaController {

    private CartaoRepository cartaoRepository;
    private BiometriaRepository biometriaRepository;
    private final Tracer tracer;

    @Autowired
    public CadastroBiometriaController(CartaoRepository cartaoRepository, BiometriaRepository biometriaRepository, Tracer tracer) {
        this.cartaoRepository = cartaoRepository;
        this.biometriaRepository = biometriaRepository;
        this.tracer = tracer;
    }

    @PostMapping("/{id}/biometrias")
    @Transactional
    public ResponseEntity<Void> cadastrarBiometria(@RequestBody @Valid BiometriaRequest request, @PathVariable Long id, @AuthenticationPrincipal Jwt usuario) {
        Span span = tracer.activeSpan();
        span.setTag("user.email", (String) usuario.getClaims().get("email"));
        if(cartaoRepository.existsById(id)) {
            throw new ApiException("Cartão não encontrado", HttpStatus.NOT_FOUND);
        }

        Cartao cartao = cartaoRepository.getOne(id);

        if(!usuario.getClaims().get("email").equals(cartao.getEmailProposta())) {
            throw  new ApiException("Cartão não pertence ao Usuário", HttpStatus.UNAUTHORIZED);
        }

        Biometria biometria = request.toModel(cartao);
        biometriaRepository.save(biometria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
