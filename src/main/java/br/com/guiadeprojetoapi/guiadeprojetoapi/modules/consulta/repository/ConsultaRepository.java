package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    List<Consulta> findByUsuarioId(Integer usuarioId);
}
