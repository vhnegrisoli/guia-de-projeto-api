package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.ETipoAcesso;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.TipoAcesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoAcessoRepository extends JpaRepository<TipoAcesso, Integer> {

    List<TipoAcesso> findByCodigoNotIn(ETipoAcesso tipoAcesso);

}
