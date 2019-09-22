package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.service.ZrDetalhamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/zona-residencial/detalhamento")
public class ZrDetalhamentoController {

    @Autowired
    private ZrDetalhamentoService zrDetalhamentoService;

    @GetMapping
    List<ZrDetalhamento> buscarTodos() {
        return zrDetalhamentoService.buscarTodos();
    }

    @GetMapping("{zonaResidencialId}")
    List<ZrDetalhamento> buscarUm(@PathVariable Integer zonaResidencialId) {
        return zrDetalhamentoService.buscarUm(zonaResidencialId);
    }
}
