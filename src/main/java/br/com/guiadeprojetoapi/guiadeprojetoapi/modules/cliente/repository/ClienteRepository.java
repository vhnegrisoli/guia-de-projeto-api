package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByUsuarioId(Integer usuarioId);

    Optional<Cliente> findByIdAndUsuarioId(Integer id, Integer usuarioId);

    Optional<Cliente> findByNome(String nome);

    Optional<Cliente> findByCpf(String cpf);
}
