package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteRequest {

    private Integer id;
    private String cpf;
    private String nome;
    private String email;

}
