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
@Table(name = "ENDERECO")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "RUA")
    @NotNull
    private String rua;

    @Column(name = "CEP")
    @NotNull
    private String cep;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @Column(name = "NUMERO")
    @NotNull
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "FK_CIDADE")
    @NotNull
    private Cidade cidade;
}
