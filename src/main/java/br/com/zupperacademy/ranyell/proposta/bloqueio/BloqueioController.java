package br.com.zupperacademy.ranyell.proposta.bloqueio;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.cartao.EstadoCartao;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;


@RestController
@RequestMapping("api/cartoes")
public class BloqueioController {

    private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    private CartaoRepository cartaoRepository;
    private BloqueioRepository bloqueioRepository;
    private SolicitaBloqueio solicitaBloqueio;
    private final Tracer tracer;

    @Autowired
    public BloqueioController(CartaoRepository cartaoRepository, BloqueioRepository bloqueioRepository, SolicitaBloqueio solicitaBloqueio, Tracer tracer) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.solicitaBloqueio = solicitaBloqueio;
        this.tracer = tracer;
    }

    @PostMapping("/{id}/bloqueios")
    @Transactional
    public ResponseEntity<Void> bloquearCartao(HttpServletRequest servletRequest, @PathVariable Long id,
                                            @AuthenticationPrincipal Jwt usuario) {
        Span span = tracer.activeSpan();
        span.setTag("user.email", (String) usuario.getClaims().get("email"));
        if(!cartaoRepository.existsById(id)) {
            throw new ApiException("Cartão não encontrado", HttpStatus.NOT_FOUND);
        }
        Cartao cartao = cartaoRepository.getOne(id);
        if(!usuario.getClaims().get("email").equals(cartao.getEmailProposta())) {
            throw  new ApiException("Cartão não pertence ao Usuário", HttpStatus.UNAUTHORIZED);
        }

        if(cartao.getEstadoCartao() == EstadoCartao.BLOQUEADO) {
            throw new ApiException("Cartão já está Bloqueado", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Bloqueio bloqueio = new Bloqueio(servletRequest.getRemoteAddr(), servletRequest.getHeader("User-Agent"), cartao);
        solicitaBloqueio.bloqueiaCartao(bloqueio);
        bloqueioRepository.save(bloqueio);
        cartao.bloquearCartao();
        logger.info("Bloqueio do cartão de id = {} realizado com sucesso", cartao.getId());
        return ResponseEntity.ok().build();
    }
}

