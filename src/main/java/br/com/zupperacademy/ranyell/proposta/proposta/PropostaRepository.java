package br.com.zupperacademy.ranyell.proposta.proposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropostaRepository  extends JpaRepository<Proposta, Long> {
}