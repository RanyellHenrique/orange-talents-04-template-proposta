package br.com.zupperacademy.ranyell.proposta.aviso;

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
public class CadastroAvisoController {

    private CartaoRepository cartaoRepository;
    private AvisoRepository avisoRepository;
    private SolicitaAviso solicitaAviso;

    @Autowired
    public CadastroAvisoController(CartaoRepository cartaoRepository, AvisoRepository avisoRepository,
                                   SolicitaAviso solicitaAviso) {
        this.cartaoRepository = cartaoRepository;
        this.avisoRepository = avisoRepository;
        this.solicitaAviso = solicitaAviso;
    }

    @PostMapping("{id}/avisos")
    @Transactional
    public ResponseEntity<Void> cadastraAviso(@Valid @RequestBody AvisoRequest request,
                                              HttpServletRequest servletRequest, @PathVariable Long id) {
        if(!cartaoRepository.existsById(id)) {
            throw new ApiException("Cartão não existe", HttpStatus.NOT_FOUND);
        }
        Cartao cartao = cartaoRepository.getOne(id);
        Aviso aviso = request.toModel(servletRequest.getHeader("user-agent"), servletRequest.getRemoteAddr(), cartao);
        solicitaAviso.avisaSistema(aviso);
        avisoRepository.save(aviso);
        return  ResponseEntity.ok().build();
    }
}
