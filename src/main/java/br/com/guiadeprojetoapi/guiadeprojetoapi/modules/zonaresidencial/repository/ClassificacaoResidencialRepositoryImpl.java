package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ClassificacaoResidencial;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.QClassificacaoResidencial.classificacaoResidencial;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.QZrDetalhamento.zrDetalhamento;

@Repository
public class ClassificacaoResidencialRepositoryImpl implements ClassificacaoResidencialRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ClassificacaoResidencial> findByZonaResidencialId(Integer zonaResidencialId) {
        return new JPAQuery<Void>(entityManager)
            .select(classificacaoResidencial)
            .from(classificacaoResidencial)
            .leftJoin(zrDetalhamento)
            .on(zrDetalhamento.classificacaoResidencial.id.eq(classificacaoResidencial.id))
            .where(zrDetalhamento.zonaResidencial.id.eq(zonaResidencialId))
            .fetch();
    }
}
