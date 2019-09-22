package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {
}
