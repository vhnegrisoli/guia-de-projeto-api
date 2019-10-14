package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer>, ConsultaRepositoryCustom {
}
