package br.edu.fatecpg.soldi.repository;

import br.edu.fatecpg.soldi.model.TipoTransacao;
import br.edu.fatecpg.soldi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    @Query("SELECT t FROM Transacao t WHERE t.uuidExterno = :uuidExterno AND t.usuario.uuidExterno = :uuidUsuario")
    Optional<Transacao> findByUuidExternoAndUsuarioUuidExterno(@Param("uuidExterno") UUID uuidExterno, @Param("uuidUsuario") UUID uuidUsuario);

    List<Transacao> findTop5ByUsuario_UuidExternoOrderByDataTransacaoDesc(UUID uuidExterno);

    List<Transacao> findTop30ByUsuario_UuidExternoOrderByDataTransacaoDesc(UUID uuidExterno);

    @Query("SELECT t FROM Transacao t WHERE t.usuario.uuidExterno = :uuidUsuario")
    List<Transacao> findAllByUsuarioUuid(@Param("uuidUsuario") UUID uuidUsuario);

    @Query("SELECT t FROM Transacao t WHERE t.usuario.uuidExterno = :uuidUsuario AND t.tipo = :tipo")
    List<Transacao> findByUsuarioUuidAndTipo(@Param("uuidUsuario") UUID uuidUsuario, @Param("tipo") TipoTransacao tipo);
}
