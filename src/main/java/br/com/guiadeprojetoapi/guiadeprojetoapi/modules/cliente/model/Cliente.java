package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto.ClienteRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

import static org.springframework.util.ObjectUtils.isEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "CLIENTE")
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

    @Column(name = "DATA_CADASTRO")
    @NotNull
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "FK_USUARIO")
    @NotNull
    private Usuario usuario;

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }

    public static Cliente of(ClienteRequest request, Integer usuarioId) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(request, cliente);
        cliente.setDataCadastro(LocalDateTime.now());
        cliente.setUsuario(new Usuario(usuarioId));
        return cliente;
    }
}
