package br.com.zupperacademy.ranyell.proposta.avisodeviagem;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/cartoes")
public class CadastroAvisoDeViagemController {

    private CartaoRepository cartaoRepository;
    private AvisoDeViagemRepository avisoDeViagemRepository;

    @Autowired
    public CadastroAvisoDeViagemController(CartaoRepository cartaoRepository,
                                           AvisoDeViagemRepository avisoDeViagemRepository) {
        this.cartaoRepository = cartaoRepository;
        this.avisoDeViagemRepository = avisoDeViagemRepository;
    }

    @PostMapping("{id}/avisos-de-viagens")
    @Transactional
    public ResponseEntity<Void> cadastraAviso(@Valid @RequestBody AvisoDeViagemRequest request,
                                              HttpServletRequest servletRequest, @PathVariable Long id) {
        if(!cartaoRepository.existsById(id)) {
            throw new ApiException("Cartão não existe", HttpStatus.NOT_FOUND);
        }
        Cartao cartao = cartaoRepository.getOne(id);
        AvisoDeViagem avisoDeViagem = request.toModel(servletRequest.getHeader("user-agent"), servletRequest.getRemoteAddr(), cartao);
        avisoDeViagemRepository.save(avisoDeViagem);
        return  ResponseEntity.ok().build();
    }
}
