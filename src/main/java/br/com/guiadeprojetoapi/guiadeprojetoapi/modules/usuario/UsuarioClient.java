package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    contextId = "usuarioClient",
    name = "usuarioAutenticadoClient",
    url = "${app-config.services.biot-admin.url}")
public interface UsuarioClient {

    @GetMapping("/api/usuarios/usuario-autenticado")
    UsuarioAutenticado getUsuarioAutenticado();

}
