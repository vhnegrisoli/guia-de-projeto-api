package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.dto.EnderecoRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static org.springframework.util.ObjectUtils.isEmpty;

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

    @Column(name = "BAIRRO")
    @NotNull
    private String bairro;

    @Column(name = "NUMERO")
    @NotNull
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "FK_CIDADE")
    @NotNull
    private Cidade cidade;

    public static Endereco of(EnderecoRequest request, Cidade cidade) {
        return Endereco
            .builder()
            .id(isEmpty(request.getId()) ? null : request.getId())
            .cidade(cidade)
            .cep(request.getCep())
            .complemento(request.getComplemento())
            .bairro(request.getBairro())
            .rua(request.getLogradouro())
            .numero(request.getNumero())
            .build();
    }
}
