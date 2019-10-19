package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.integracaosiglon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaLocalizacaoRequest {

    private String xmin;
    private String xmax;
    private String ymin;
    private String ymax;
    private String wkid;
}
