package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsuarioResponse {

    private Integer id;
    private String nome;
    private String email;

    public static UsuarioResponse of(Usuario usuario) {
        var response = new UsuarioResponse();
        BeanUtils.copyProperties(usuario, response);
        return response;
    }

}
