package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
