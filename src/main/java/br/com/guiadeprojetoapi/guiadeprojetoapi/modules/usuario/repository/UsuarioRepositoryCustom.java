package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado;

public interface UsuarioRepositoryCustom {

    UsuarioAutenticado findUsuarioAutenticadoByEmail(String email);

}
