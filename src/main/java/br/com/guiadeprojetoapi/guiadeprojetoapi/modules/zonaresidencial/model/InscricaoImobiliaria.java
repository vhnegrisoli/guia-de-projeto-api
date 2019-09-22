package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco;
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
@Table(name = "INSCRICAO_IMOBILIARIA")
public class InscricaoImobiliaria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO_INSCRICAO")
    @NotNull
    private String codigoInscricao;

    @Column(name = "QUADRA")
    @NotNull
    private String quadra;

    @Column(name = "DATA")
    @NotNull
    private String data;

    @ManyToOne
    @JoinColumn(name = "FK_ENDERECO")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "FK_ZONA_RESIDENCIAL")
    private ZonaResidencial zonaResidencial;
}
