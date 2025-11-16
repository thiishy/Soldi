package br.edu.fatecpg.soldi.repository;

import br.edu.fatecpg.soldi.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {


    @Query("SELECT t FROM Transacao t WHERE t.usuario.uuid_externo = :uuidUsuario ORDER BY t.data_transacao DESC LIMIT 5")
    List<Transacao> buscarUltimas5(@Param("uuidUsuario") UUID uuidUsuario);


    @Query("SELECT t FROM Transacao t WHERE t.usuario.uuid_externo = :uuidUsuario")
    List<Transacao> findAllByUsuarioUuid(@Param("uuidUsuario") UUID uuidUsuario);


    @Query("SELECT t FROM Transacao t WHERE t.usuario.uuid_externo = :uuidUsuario AND t.tipo = :tipo")
    List<Transacao> findByUsuarioUuidAndTipo(@Param("uuidUsuario") UUID uuidUsuario, @Param("tipo") String tipo);
}