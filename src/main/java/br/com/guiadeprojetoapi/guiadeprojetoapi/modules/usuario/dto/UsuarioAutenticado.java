package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.EPermissao;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.ETipoAcesso;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.EPermissao.BIOT_ADMIN;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.EPermissao.USER;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioAutenticado {

    private Integer id;
    private String nome;
    private String email;
    private String cpf;
    private EPermissao permissao;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime ultimoAcesso;
    private ETipoAcesso tipoAcesso;
    private String descricaoAcesso;

    public boolean isAdmin() {
        return permissao.equals(BIOT_ADMIN);
    }

    public boolean isUser() {
        return permissao.equals(USER);
    }

    public static UsuarioAutenticado of(Usuario usuario) {
        var usuarioAutenticado = new UsuarioAutenticado();
        BeanUtils.copyProperties(usuario, usuarioAutenticado);
        usuarioAutenticado.setPermissao(usuario.getPermissao().getPermissao());
        usuarioAutenticado.setDescricao(usuario.getPermissao().getDescricao());
        usuarioAutenticado.setTipoAcesso(usuario.getTipoAcesso().getCodigo());
        usuarioAutenticado.setDescricaoAcesso(usuario.getTipoAcesso().getDescricao());
        return usuarioAutenticado;
    }
}
