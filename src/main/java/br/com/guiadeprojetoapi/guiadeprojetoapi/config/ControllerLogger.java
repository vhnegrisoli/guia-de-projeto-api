package br.com.guiadeprojetoapi.guiadeprojetoapi.config;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@ControllerAdvice
@Component
public class ControllerLogger {

    @Autowired
    private LogService logService;

    @InitBinder
    public void initBinder(HttpServletRequest request) throws IOException {
        logService.gerarLogUsuario(request);
    }
}
