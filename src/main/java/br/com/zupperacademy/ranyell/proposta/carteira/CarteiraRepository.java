package br.com.zupperacademy.ranyell.proposta.carteira;

import br.com.zupperacademy.ranyell.proposta.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    boolean existsByTipoCarteiraAndCartao(TipoCarteira tipoCarteira, Cartao cartao);
}
