package br.com.zupperacademy.ranyell.proposta.avisodeviagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoDeViagemRepository extends JpaRepository<AvisoDeViagem, Long> {

}
