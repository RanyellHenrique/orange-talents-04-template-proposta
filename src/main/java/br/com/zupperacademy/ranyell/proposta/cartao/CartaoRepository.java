package br.com.zupperacademy.ranyell.proposta.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    boolean existsById(Long id);
}
