package br.edu.fatecpg.soldi.repository;

import br.edu.fatecpg.soldi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUuidExterno(UUID uuidExterno);

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}