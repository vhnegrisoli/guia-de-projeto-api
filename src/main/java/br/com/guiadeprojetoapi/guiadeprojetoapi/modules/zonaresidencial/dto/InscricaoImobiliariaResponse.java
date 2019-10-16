package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ClassificacaoResidencial;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.InscricaoImobiliaria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InscricaoImobiliariaResponse {

    private InscricaoImobiliaria inscricaoImobiliaria;
    private List<ClassificacaoResidencial> classificacoes;

    public static InscricaoImobiliariaResponse of(InscricaoImobiliaria inscricaoImobiliaria,
                                                  List<ClassificacaoResidencial> classificacoes) {
        var response = new InscricaoImobiliariaResponse();
        response.setInscricaoImobiliaria(inscricaoImobiliaria);
        response.setClassificacoes(classificacoes);
        return response;
    }
}
