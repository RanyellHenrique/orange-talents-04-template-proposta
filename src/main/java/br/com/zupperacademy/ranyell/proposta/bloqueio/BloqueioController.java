package br.com.zupperacademy.ranyell.proposta.bloqueio;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.cartao.EstadoCartao;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private NotificaSistema notificaSistema;

    @Autowired
    public BloqueioController(CartaoRepository cartaoRepository, BloqueioRepository bloqueioRepository, NotificaSistema notificaSistema) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRepository = bloqueioRepository;
        this.notificaSistema = notificaSistema;
    }

    @PostMapping("/{id}/bloqueios")
    @Transactional
    public ResponseEntity<?> bloquearCartao(HttpServletRequest servletRequest, @PathVariable Long id) {
        Cartao supostoCartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new ApiException("Cartão não encontrado", HttpStatus.NOT_FOUND));

        if(supostoCartao.getEstadoCartao() == EstadoCartao.BLOQUEADO) {
            throw new ApiException("Cartão já está Bloqueado", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Bloqueio bloqueio = new Bloqueio(servletRequest.getRemoteAddr(), servletRequest.getHeader("User-Agent"), supostoCartao);
        notificaSistema.bloqueiaCartao(bloqueio);
        bloqueioRepository.save(bloqueio);
        supostoCartao.bloquearCartao();
        logger.info("Bloqueio do cartão de id = {} realizado com sucesso", supostoCartao.getId());
        return ResponseEntity.ok().build();
    }
}

