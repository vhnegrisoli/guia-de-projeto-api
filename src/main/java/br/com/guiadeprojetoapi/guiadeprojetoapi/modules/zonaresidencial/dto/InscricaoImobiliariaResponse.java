package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.InscricaoImobiliaria;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
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
    private List<ZrDetalhamento> detalhamento;

    public static InscricaoImobiliariaResponse of(InscricaoImobiliaria inscricaoImobiliaria,
                                                  List<ZrDetalhamento> zrDetalhamento) {
        var response = new InscricaoImobiliariaResponse();
        response.setInscricaoImobiliaria(inscricaoImobiliaria);
        response.setDetalhamento(zrDetalhamento);
        return response;
    }

}
