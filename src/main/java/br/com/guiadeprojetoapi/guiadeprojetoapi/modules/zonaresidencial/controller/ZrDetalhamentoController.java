package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.SubZonaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums.EZonaResidencial;
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

    @GetMapping("/inscricao-imobiliaria/{inscricaoImobiliariaCodigo}")
    public InscricaoImobiliariaResponse buscarPorInscricaoImobiliaria(@PathVariable String inscricaoImobiliariaCodigo) {
        return zrDetalhamentoService.buscarPorInscricaoImobiliaria(inscricaoImobiliariaCodigo);
    }

    @GetMapping("{zonaResidencial}")
    public SubZonaResponse buscarPorZonaResidencial(@PathVariable(name = "zonaResidencial")
                                                                           EZonaResidencial codigo) {
        return zrDetalhamentoService.buscarPorZonaResidencial(codigo);
    }

    @GetMapping("/cep/{cep}")
    public InscricaoImobiliariaResponse buscarPorCep(@PathVariable String cep) {
        return zrDetalhamentoService.buscarPorCep(cep);
    }

    @GetMapping("zoneamento/{zonaResidencialId}/sub-zoneamento/{classificacaoResidencialId}")
    public List<ZrDetalhamento> buscarDetalhamentos(@PathVariable Integer zonaResidencialId,
                                                    @PathVariable Integer classificacaoResidencialId) {
        return zrDetalhamentoService.buscarDetalhamentos(zonaResidencialId, classificacaoResidencialId);
    }
}