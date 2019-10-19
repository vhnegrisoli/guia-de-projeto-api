package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model;

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
@Table(name = "OBRA")
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME")
    @NotNull
    private String nome;

    @Column(name = "DESCRICAO")
    @NotNull
    private String descricao;

    @JoinColumn(name = "FK_CLIENTE")
    @ManyToOne
    @NotNull
    Cliente cliente;
}
