package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository.EnderecoRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.model.ZrDetalhamento;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ClassificacaoResidencialRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.InscricaoImobiliariaRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ZrDetalhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse.of;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ZrDetalhamentoService {

    @Autowired
    private ZrDetalhamentoRepository detalhamentoRepository;
    @Autowired
    private ClassificacaoResidencialRepository classificacaoResidencialRepository;
    @Autowired
    private InscricaoImobiliariaRepository inscricaoImobiliariaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public InscricaoImobiliariaResponse buscarPorInscricaoImobiliaria(String inscricaoImobiliariaCodigo) {

        var inscricaoImobiliaria = inscricaoImobiliariaRepository
            .findByCodigoInscricao(inscricaoImobiliariaCodigo)
            .orElseThrow(() -> new ValidacaoException("Essa inscrição imobiliária não existe."));

        var classificacoes = classificacaoResidencialRepository
            .findByZonaResidencialId(inscricaoImobiliaria.getZonaResidencial().getId());

        return of(inscricaoImobiliaria, classificacoes);
    }

    public InscricaoImobiliariaResponse buscarPorCep(String cep) {

        var enderecos = enderecoRepository.findByCep(cep);
        validarEnderecos(enderecos);

        var inscricaoImobiliaria = inscricaoImobiliariaRepository
            .findByEnderecoId(enderecos.get(0).getId())
            .orElseThrow(() -> new ValidacaoException("Essa inscrição imobiliária não existe."));

        var classificacoes = classificacaoResidencialRepository
            .findByZonaResidencialId(inscricaoImobiliaria.getZonaResidencial().getId());

        return of(inscricaoImobiliaria, classificacoes);
    }

    private void validarEnderecos(List<Endereco> enderecos) {
        if (isEmpty(enderecos) || enderecos.isEmpty()) {
            throw  new ValidacaoException("Não existem endereços cadastrado para esse CEP.");
        }
    }

    public List<ZrDetalhamento> buscarDetalhamentos(Integer zonaResidencialId, Integer classificacaoResidencialId) {
        return detalhamentoRepository
            .findByZonaResidencialIdAndClassificacaoResidencialId(zonaResidencialId, classificacaoResidencialId);
    }
}
