package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ClassificacaoResidencial;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZonaResidencial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SubZonaResponse {

    ZonaResidencial zonaResidencial;
    List<ClassificacaoResidencial> classificacoesResidenciais;

    public static SubZonaResponse of(List<ClassificacaoResidencial> classificacoesResidenciais,
                                     ZonaResidencial zonaResidencial) {
        return SubZonaResponse
            .builder()
            .classificacoesResidenciais(classificacoesResidenciais)
            .zonaResidencial(zonaResidencial)
            .build();
    }
}
