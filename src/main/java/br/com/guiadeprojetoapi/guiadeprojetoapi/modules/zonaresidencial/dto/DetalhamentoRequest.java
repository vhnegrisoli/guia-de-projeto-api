package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetalhamentoRequest {

    private Integer zonaResidencialId;
    private Integer classificacaoResidencialId;
    private Double area;

}
