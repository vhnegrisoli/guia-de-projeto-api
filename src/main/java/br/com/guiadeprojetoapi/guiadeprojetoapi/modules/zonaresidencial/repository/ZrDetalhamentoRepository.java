package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZrDetalhamentoRepository extends JpaRepository<ZrDetalhamento, Integer> {

    List<ZrDetalhamento> findByZonaResidencialId(Integer zonaResidencialId);

}
