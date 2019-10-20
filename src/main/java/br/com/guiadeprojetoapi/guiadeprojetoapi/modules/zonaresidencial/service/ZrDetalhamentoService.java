package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository.EnderecoRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.DetalhamentoRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.DetalhamentoResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.SubZonaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.enums.EZonaResidencial;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ClassificacaoResidencialRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.InscricaoImobiliariaRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ZonaResidencialRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.ZrDetalhamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.DetalhamentoResponse.of;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.dto.InscricaoImobiliariaResponse.of;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception.ZonaResidencialException.DETALHAMENTO_NAO_ENCONTRADO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception.ZonaResidencialException.ZONA_RESIDENCIAL_NAO_ENCONTRADA;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@SuppressWarnings("PMD.TooManyStaticImports")
public class ZrDetalhamentoService {

    @Autowired
    private ZrDetalhamentoRepository detalhamentoRepository;
    @Autowired
    private ClassificacaoResidencialRepository classificacaoResidencialRepository;
    @Autowired
    private InscricaoImobiliariaRepository inscricaoImobiliariaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ZonaResidencialRepository zonaResidencialRepository;

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

    public DetalhamentoResponse buscarDetalhamentos(DetalhamentoRequest request) {
        return of(detalhamentoRepository
            .findByZonaResidencialIdAndClassificacaoResidencialId(request.getZonaResidencialId(),
                request.getClassificacaoResidencialId())
            .orElseThrow(DETALHAMENTO_NAO_ENCONTRADO::getException), request.getArea());
    }

    public SubZonaResponse buscarPorZonaResidencial(EZonaResidencial codigo) {
        var zonaResidencial = zonaResidencialRepository.findByCodigo(codigo)
            .orElseThrow(ZONA_RESIDENCIAL_NAO_ENCONTRADA::getException);
        return SubZonaResponse.of(classificacaoResidencialRepository
            .findByZonaResidencialId(zonaResidencial.getId()), zonaResidencial);
    }
}
