package br.com.zupperacademy.ranyell.proposta.bloqueio;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import br.com.zupperacademy.ranyell.proposta.cartao.CartaoRepository;
import br.com.zupperacademy.ranyell.proposta.cartao.EstadoCartao;
import br.com.zupperacademy.ranyell.proposta.compartilhado.exceptions.ApiException;
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

    private CartaoRepository cartaoRepository;
    private BloqueioRepository bloqueioRepository;

    @Autowired
    public BloqueioController(CartaoRepository cartaoRepository, BloqueioRepository bloqueioRepository) {
        this.cartaoRepository = cartaoRepository;
        this.bloqueioRepository = bloqueioRepository;
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
        bloqueioRepository.save(bloqueio);
        supostoCartao.bloquearCartao();

        return ResponseEntity.ok().build();
    }
}

