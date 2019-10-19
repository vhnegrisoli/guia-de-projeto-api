package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.integracaosiglon.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.integracaosiglon.dto.ConsultaLocalizacaoRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.integracaosiglon.service.IntegracaoService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/integracao-siglon")
public class IntegracaoController {
    @Autowired
    private IntegracaoService integracaoService;

    @PostMapping("zoneamento")
    public JSONObject recuperarEndereco(@RequestBody ConsultaLocalizacaoRequest request) throws IOException,
        ParseException {
        return integracaoService.getDadosApiSiglon(request);
    }
}
