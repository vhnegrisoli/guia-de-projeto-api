package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums.EClassificacaoResidencial;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "CLASSIFICACAO_RESIDENCIAL")
public class ClassificacaoResidencial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private EClassificacaoResidencial codigo;

    @Column
    private String descricao;
}
