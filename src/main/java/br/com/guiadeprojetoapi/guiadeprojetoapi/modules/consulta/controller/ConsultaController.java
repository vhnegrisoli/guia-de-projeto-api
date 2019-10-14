package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.service.ConsultaService;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping
    public InscricaoImobiliariaResponse save(@RequestBody ConsultaRequest request) {
        return consultaService.save(request);
    }

    @GetMapping
    public List<ConsultaResponse> buscarConsultas() {
        return consultaService.buscarConsultas();
    }

}
