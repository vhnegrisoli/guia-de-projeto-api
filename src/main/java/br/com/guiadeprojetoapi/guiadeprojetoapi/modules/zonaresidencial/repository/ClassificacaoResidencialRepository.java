package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ClassificacaoResidencial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificacaoResidencialRepository extends JpaRepository<ClassificacaoResidencial, Integer> {
}
