package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model;

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
@Table(name = "ESTADO")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO_IBGE")
    @NotNull
    private Integer codigoIbge;

    @Column(name = "ESTADO")
    @NotNull
    private String estado;

    @Column(name = "SIGLA")
    @NotNull
    private String sigla;

    @ManyToOne
    @JoinColumn(name = "FK_REGIAO")
    @NotNull
    private Regiao regiao;
}
