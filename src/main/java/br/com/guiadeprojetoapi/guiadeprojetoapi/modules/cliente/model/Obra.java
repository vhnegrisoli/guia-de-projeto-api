package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto.ObraRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.springframework.util.ObjectUtils.isEmpty;

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

    @JsonIgnore
    public boolean isNovoCadastro() {
        return isEmpty(id);
    }

    public static Obra of(ObraRequest request, Cliente cliente) {
        var obra = new Obra();
        BeanUtils.copyProperties(request, obra);
        obra.setCliente(cliente);
        return obra;
    }
}
