package br.edu.fatecpg.soldi.repository;

import br.edu.fatecpg.soldi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
