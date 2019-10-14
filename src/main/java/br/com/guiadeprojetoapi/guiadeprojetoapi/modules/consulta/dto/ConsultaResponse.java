package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.model.Consulta;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ConsultaResponse {

    private Integer id;
    private String codigoInscricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataConsulta;

    public static ConsultaResponse of(Consulta consulta) {
        var response = new ConsultaResponse();
        BeanUtils.copyProperties(consulta, response);
        return response;
    }
}
