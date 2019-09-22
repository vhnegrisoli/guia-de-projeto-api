package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.ETipoAcesso;
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
@Table(name = "TIPO_ACESSO")
public class TipoAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "CODIGO")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ETipoAcesso codigo;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;
}
