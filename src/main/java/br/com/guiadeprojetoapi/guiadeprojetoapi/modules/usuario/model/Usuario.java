package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.EPermissao.USER;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "EMAIL")
    @NotNull
    private String email;

    @Column(name = "NOME", length = 120)
    @NotNull
    private String nome;

    @Column(name = "SENHA")
    @NotNull
    private String senha;

    @ManyToOne
    @JoinColumn(name = "FK_PERMISSAO")
    @NotNull
    private Permissao permissao;

    @Column(name = "DATA_CADASTRO")
    @NotNull
    private LocalDateTime dataCadastro;

    @Column(name = "ULTIMO_ACESSO")
    @NotNull
    private LocalDateTime ultimoAcesso;

    @Column(name = "CPF", length = 14)
    @CPF
    private String cpf;

    public static Usuario of(UsuarioRequest usuarioRequest) {
        var usuario = new Usuario();
        BeanUtils.copyProperties(usuarioRequest, usuario);
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuario.setPermissao(new Permissao(1, USER, "Usuário"));
        return usuario;
    }
}
