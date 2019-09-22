package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.InscricaoImobiliariaRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ZrDetalhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse.of;

@Service
public class ZrDetalhamentoService {

    @Autowired
    private ZrDetalhamentoRepository detalhamentoRepository;
    @Autowired
    private InscricaoImobiliariaRepository inscricaoImobiliariaRepository;

    public List<ZrDetalhamento> buscarTodos() {
        return detalhamentoRepository.findAll();
    }

    public List<ZrDetalhamento> buscarUm(Integer id) {
        return detalhamentoRepository.findByZonaResidencialId(id);
    }

    public InscricaoImobiliariaResponse buscarPorInscricaoImobiliaria(String inscricaoImobiliariaCodigo) {
        var inscricaoImobiliaria = inscricaoImobiliariaRepository
            .findByCodigoInscricao(inscricaoImobiliariaCodigo)
            .orElseThrow(() -> new ValidacaoException("Essa inscrição imobiliária não existe."));
        var detalhamento = detalhamentoRepository
            .findByZonaResidencialId(inscricaoImobiliaria.getZonaResidencial().getId());
        return of(inscricaoImobiliaria, detalhamento);
    }
}
