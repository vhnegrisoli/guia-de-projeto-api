package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.InscricaoImobiliaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InscricaoImobiliariaRepository extends JpaRepository<InscricaoImobiliaria, Integer> {

    Optional<InscricaoImobiliaria> findByCodigoInscricao(String codigoInscricao);
}
