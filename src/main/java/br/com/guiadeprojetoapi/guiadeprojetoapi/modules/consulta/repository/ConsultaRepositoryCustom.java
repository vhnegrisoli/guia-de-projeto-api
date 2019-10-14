package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaResponse;

import java.util.List;

public interface ConsultaRepositoryCustom {

    List<ConsultaResponse> findByUsuarioId(Integer usuarioId);
}
