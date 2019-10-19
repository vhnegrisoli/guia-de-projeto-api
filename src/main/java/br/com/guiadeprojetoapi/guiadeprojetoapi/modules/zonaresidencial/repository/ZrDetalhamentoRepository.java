package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZrDetalhamentoRepository extends JpaRepository<ZrDetalhamento, Integer> {

    Optional<ZrDetalhamento> findByZonaResidencialIdAndClassificacaoResidencialId(Integer zonaResidencialId,
                                                                                 Integer classificacaoResidencialId);
}
