package br.com.zupperacademy.ranyell.proposta.carteira;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/cartoes")
public class CadastroCarteiraController {

    private CartaoRepository cartaoRepository;
    private SolicitaNovaCarteira solicitaNovaCarteira;
    private CarteiraRepository carteiraRepository;

    @Autowired
    public CadastroCarteiraController(CartaoRepository cartaoRepository, SolicitaNovaCarteira solicitaNovaCarteira,
                                      CarteiraRepository carteiraRepository) {
        this.cartaoRepository = cartaoRepository;
        this.solicitaNovaCarteira = solicitaNovaCarteira;
        this.carteiraRepository = carteiraRepository;
    }

    @PostMapping("/{id}/carteiras")
    public ResponseEntity<Void> cadastrarCarteira(@Valid @RequestBody  CarteiraRequest request, @PathVariable Long id,
                                                 @AuthenticationPrincipal Jwt usuario) {
        if(!cartaoRepository.existsById(id)) {
            throw new ApiException("Cartão não existe", HttpStatus.NOT_FOUND);
        }

        Cartao cartao = cartaoRepository.getOne(id);

        if(!usuario.getClaims().get("email").equals(cartao.getEmailProposta())) {
            throw new ApiException("Cartão não pertence ao usuário", HttpStatus.UNAUTHORIZED);
        }
        if(carteiraRepository.existsByTipoCarteiraAndCartao(request.getTipoCarteira(), cartao)){
            throw new ApiException("Carteira digital já está associada a esse cartão", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Carteira novaCarteira = request.toModel(cartao);
        solicitaNovaCarteira.criaCarteira(novaCarteira);
        carteiraRepository.save(novaCarteira);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(novaCarteira.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
