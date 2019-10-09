package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, UsuarioRepositoryCustom {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByCpf(String cpf);
}
