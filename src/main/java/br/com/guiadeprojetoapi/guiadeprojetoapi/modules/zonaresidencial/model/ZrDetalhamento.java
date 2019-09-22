package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ZR_DETALHAMENTO")
public class ZrDetalhamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "FK_CLASSIFICACAO_RESIDENCIAL")
    @NotNull
    private ClassificacaoResidencial classificacaoResidencial;

    @ManyToOne
    @JoinColumn(name = "FK_ZONA_RESIDENCIAL")
    @NotNull
    private ZonaResidencial zonaResidencial;

    @Column(name = "MAX_UNIDADES_HABITACIONAIS")
    private Integer maxUnidadesHabitacionais;

    @Column(name = "MAX_UNIDADES_SOBREPOSTAS")
    private Integer maxUnidadesSobrepostas;

    @Column(name = "DATA_MINIMA")
    private Integer dataMinima;

    @Column(name = "MEIA_QUADRA")
    private Integer meiaQuadra;

    @Column(name = "ESQUINA")
    private Integer esquina;

    @Column(name = "TAXA_OCUPACAO")
    private Double taxaOcupacao;

    @Column(name = "COEFICIENTE_MINIMO")
    private Double coeficienteMinimo;

    @Column(name = "COEFICIENTE_BASICO")
    private Double coeficienteBasico;

    @Column(name = "COEFICIENTE_MAXIMO")
    private Double coeficienteMaximo;

    @Column(name = "ALTURA_MAXIMA")
    private Double alturaMaxima;

    @Column(name = "RECUO_MINIMO")
    private Double recuoMinimo;

    @Column(name = "AFASTAMENTO_MIN_ABERTURA")
    private Double afastamentoMinAbertura;

    @Column(name = "AFASTAMENTO_MIN_EDIFICACAO")
    private Double afastamentoMinEdificacao;

    @Column(name = "AFASTAMENTO_MIN_VIAS_PARTICULARES")
    private Double afastamentoMinViasParticulares;

    @Column(name = "AFASTAMENTO_MINIMO")
    private Double afastamentoMinimo;

    @Column(name = "MINIMO_VAGAS")
    private Integer minimoVagas;

    @Column(name = "OBSERVACOES")
    private String observacoes;

}
