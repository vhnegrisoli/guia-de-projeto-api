package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.dto.LogRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.logMqSender.LogSender;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.config.Aplicacao.APLICACAO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.enums.ETipoOperacao.CONSULTANDO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.enums.ETipoOperacao.SALVANDO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.enums.ETipoOperacao.REMOVENDO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.enums.ETipoOperacao.ALTERANDO;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Service
@SuppressWarnings("PMD.TooManyStaticImports")
public class LogService {

    private static final List<String> URLS_IGNORADAS = List.of(
        "/api/usuarios/usuario-autenticado",
        "/api/usuarios/check-session",
        "/api/usuarios/tipos-acesso",
        "/oauth/token"
    );

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LogSender logSender;

    public void gerarLogUsuario(HttpServletRequest request) throws IOException {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        if (!URLS_IGNORADAS.contains(request.getRequestURI())) {
            var log = LogRequest
                .builder()
                .dataAcesso(LocalDateTime.now())
                .aplicacao(APLICACAO)
                .tipoOperacao(definirTipoAcesso(request.getMethod()))
                .metodo(request.getMethod())
                .urlAcessada(URLS_IGNORADAS.contains(request.getRequestURI()) ? "" : request.getRequestURI())
                .usuarioId(usuarioLogado.getId())
                .usuarioNome(usuarioLogado.getNome())
                .usuarioEmail(usuarioLogado.getEmail())
                .build();
            logSender.produceMessage(log);
        }
    }

    private String definirTipoAcesso(String metodo) {
        if (metodo.equals(GET.name())) {
            return CONSULTANDO.getOperacao();
        } else if (metodo.equals(POST.name())) {
            return SALVANDO.getOperacao();
        } else if (metodo.equals(PUT.name())) {
            return ALTERANDO.getOperacao();
        } else {
            return REMOVENDO.getOperacao();
        }
    }
}