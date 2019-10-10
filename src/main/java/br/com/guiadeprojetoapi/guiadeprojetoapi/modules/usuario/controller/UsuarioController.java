package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.TipoAcesso;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository.TipoAcessoRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.ETipoAcesso.B2VN;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    @GetMapping("/tipos-acesso")
    public List<TipoAcesso> buscarTodosTiposAcesso() {
        return tipoAcessoRepository.findByCodigoNotIn(B2VN);
    }

    @GetMapping("/check-session")
    public ResponseEntity checkSession() {
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping("/novo")
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Usuário inserido com sucesso!")
    public void novoUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
    }

    @PutMapping("/alterar-acesso")
    @ResponseStatus(code = HttpStatus.OK, reason = "Usuário alterado com sucesso!")
    public void alterarDadosUsuario(@RequestBody UsuarioRequest usuarioRequest) {
        usuarioService.save(usuarioRequest);
    }

    @GetMapping("/usuario-autenticado")
    public UsuarioAutenticado getUsuarioAutenticado() {
        return usuarioService.getUsuarioAutenticadoAtualizaUltimaData();
    }

    @GetMapping("/logado")
    public UsuarioAutenticado findUsuarioAutenticadoByEmail() {
        return usuarioService.findUsuarioAutenticadoByEmail();
    }
}
