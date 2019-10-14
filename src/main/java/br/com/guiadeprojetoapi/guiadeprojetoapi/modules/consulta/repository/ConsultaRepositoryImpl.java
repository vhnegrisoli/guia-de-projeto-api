package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.model.QConsulta.consulta;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.QInscricaoImobiliaria.inscricaoImobiliaria;

@Repository
public class ConsultaRepositoryImpl implements ConsultaRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ConsultaResponse> findByUsuarioId(Integer usuarioId) {
        return new JPAQuery<Void>(entityManager)
            .select(
                Projections.constructor(
                    ConsultaResponse.class,
                    consulta.id,
                    inscricaoImobiliaria.codigoInscricao,
                    consulta.dataConsulta))
            .from(consulta)
            .leftJoin(consulta.inscricaoImobiliaria, inscricaoImobiliaria)
            .where(consulta.usuario.id.eq(usuarioId))
            .fetch();
    }
}