package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
