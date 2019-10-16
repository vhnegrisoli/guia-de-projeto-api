package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ClassificacaoResidencial;

import java.util.List;

public interface ClassificacaoResidencialRepositoryCustom {

    List<ClassificacaoResidencial> findByZonaResidencialId(Integer zonaResidencialId);
}
