package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    private String nome;
    private String email;
    private String cpf;
    private String senha;

}
