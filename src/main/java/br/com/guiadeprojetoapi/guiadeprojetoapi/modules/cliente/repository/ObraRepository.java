package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Obra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObraRepository extends JpaRepository<Obra, Integer> {

    Optional<Obra> findByIdAndClienteId(Integer id, Integer clienteId);

    Optional<Obra> findByNome(String nome);

    List<Obra> findByClienteId(Integer clienteId);
}
