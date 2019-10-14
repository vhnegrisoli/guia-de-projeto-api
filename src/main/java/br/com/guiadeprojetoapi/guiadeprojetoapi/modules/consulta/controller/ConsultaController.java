package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Consulta realizada com sucesso!")
    public void save(@RequestBody ConsultaRequest request) {
        consultaService.save(request);
    }

    @GetMapping
    public List<ConsultaResponse> buscarConsultas() {
        return consultaService.buscarConsultas();
    }

}
