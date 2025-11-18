package br.edu.fatecpg.soldi.repository;

import br.edu.fatecpg.soldi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
