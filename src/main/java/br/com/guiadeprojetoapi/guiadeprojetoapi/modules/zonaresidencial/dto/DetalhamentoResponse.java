package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ClassificacaoResidencial;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZonaResidencial;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.BeanUtils;

import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DetalhamentoResponse {

    private static final Double ZERO = 0.0;
    private static final Integer DOIS = 2;

    // Valores da base
    private Integer id;
    private ClassificacaoResidencial classificacaoResidencial;
    private ZonaResidencial zonaResidencial;
    private Integer maxUnidadesHabitacionais;
    private Integer maxUnidadesSobrepostas;
    private Integer dataMinima;
    private Integer meiaQuadra;
    private Integer esquina;
    private Double taxaOcupacao;
    private Double coeficienteMinimo;
    private Double coeficienteBasico;
    private Double coeficienteMaximo;
    private Double alturaMaxima;
    private Double recuoMinimo;
    private Double afastamentoMinAbertura;
    private Double afastamentoMinEdificacao;
    private Double afastamentoMinViasParticulares;
    private Double afastamentoMinimo;
    private Integer minimoVagas;
    private String observacoes;
    // Valores calculados
    private Double coeficienteAproveitamentoMinimo;
    private Double coeficienteAproveitamentoBasico;
    private Double coeficienteAproveitamentoMaximo;
    private Double taxaOcupacaoMaxima;
    private Double areaLazer;

    private Double getCalculos(Double campo, Double area) {
        return Precision.round(validarValorExistente(campo) * area, DOIS);
    }

    private Double validarValorExistente(Double campo) {
        return isEmpty(campo) ? ZERO : campo;
    }

    public static DetalhamentoResponse of(ZrDetalhamento detalhamento, Double area) {
        var response = new DetalhamentoResponse();
        BeanUtils.copyProperties(detalhamento, response);
        response.setCoeficienteAproveitamentoMinimo(response.getCalculos(response.getCoeficienteMinimo(), area));
        response.setCoeficienteAproveitamentoBasico(response.getCalculos(response.getCoeficienteBasico(), area));
        response.setCoeficienteAproveitamentoMaximo(response.getCalculos(response.getCoeficienteMaximo(), area));
        response.setTaxaOcupacaoMaxima(response.getCalculos(response.getTaxaOcupacaoMaxima(), area));
        response.setAreaLazer(response.getCalculos(response.getAreaLazer(), area));
        return response;
    }
}
