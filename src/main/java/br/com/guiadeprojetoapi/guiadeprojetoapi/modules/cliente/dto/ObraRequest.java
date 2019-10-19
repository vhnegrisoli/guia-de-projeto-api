package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ObraRequest {

    private Integer id;
    private String nome;
    private String descricao;
    private Integer clienteId;

}
