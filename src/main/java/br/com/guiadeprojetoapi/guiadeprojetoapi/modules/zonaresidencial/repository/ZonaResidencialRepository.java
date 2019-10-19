package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums.EZonaResidencial;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZonaResidencial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZonaResidencialRepository extends JpaRepository<ZonaResidencial, Integer> {

    Optional<ZonaResidencial> findByCodigo(EZonaResidencial codigo);

}
