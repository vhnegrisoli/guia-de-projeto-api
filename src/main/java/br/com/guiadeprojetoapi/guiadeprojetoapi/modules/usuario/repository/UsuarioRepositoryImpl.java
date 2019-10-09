package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.QUsuario.usuario;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.QPermissao.permissao1;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.QTipoAcesso.tipoAcesso;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public UsuarioAutenticado findUsuarioAutenticadoByEmail(String email) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(
                    UsuarioAutenticado.class,
                    usuario.id,
                    usuario.nome,
                    usuario.email,
                    usuario.cpf,
                    permissao1.permissao,
                    permissao1.descricao,
                    usuario.ultimoAcesso,
                    tipoAcesso.codigo,
                    tipoAcesso.descricao))
            .from(usuario)
            .leftJoin(usuario.permissao, permissao1)
            .leftJoin(usuario.tipoAcesso, tipoAcesso)
            .where(usuario.email.eq(email))
            .fetchOne();
    }
}
