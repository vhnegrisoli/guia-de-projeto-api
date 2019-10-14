package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.dto.EnderecoRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository.CidadeRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository.EnderecoRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.exception.EnderecoException.CIDADE_NAO_ENCONTRADA;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.exception.EnderecoException.ENDERECO_NAO_ENCONTADO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco.of;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public void save(EnderecoRequest request) {
        enderecoRepository.save(of(request, cidadeRepository.findByNome(request.getLocalidade())
            .orElseThrow(CIDADE_NAO_ENCONTRADA::getException)));
    }

    public Endereco buscar(Integer id) {
        return enderecoRepository.findById(id).orElseThrow(ENDERECO_NAO_ENCONTADO::getException);
    }
}