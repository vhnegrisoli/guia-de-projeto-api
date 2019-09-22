package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ZrDetalhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZrDetalhamentoService {

    @Autowired
    private ZrDetalhamentoRepository detalhamentoRepository;

    public List<ZrDetalhamento> buscarTodos() {
        return detalhamentoRepository.findAll();
    }

    public List<ZrDetalhamento> buscarUm(Integer id) {
        return detalhamentoRepository.findByZonaResidencialId(id);
    }
}
