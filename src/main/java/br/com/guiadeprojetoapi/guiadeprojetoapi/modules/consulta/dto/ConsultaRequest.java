package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConsultaRequest {

    private Integer id;
    private String codigoInscricao;

}
