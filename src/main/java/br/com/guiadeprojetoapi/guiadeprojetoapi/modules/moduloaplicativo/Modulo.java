package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.moduloaplicativo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "MODULO")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO")
    @NotNull
    @Enumerated(EnumType.STRING)
    private EModulo codigo;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;
}
