package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", length = 120)
    @NotNull
    private String nome;

    @Column(name = "CPF")
    @NotNull
    @CPF
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    @NotNull
    private Usuario usuario;
}
