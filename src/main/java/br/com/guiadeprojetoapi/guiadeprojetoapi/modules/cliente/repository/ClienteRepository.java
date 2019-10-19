package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
