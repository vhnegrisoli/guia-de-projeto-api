package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto.ObraRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Obra;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.repository.ObraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Obra.of;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception.ZonaResidencialException.OBRA_JA_EXISTENTE;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception.ZonaResidencialException.OBRA_NAO_CADASTRADA;

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;
    @Autowired
    private ClienteService clienteService;

    public void save(ObraRequest request) {
        var cliente = clienteService.buscarUm(request.getId());
        var obra = of(request, cliente);
        validarObraJaCadastrada(obra);
        obraRepository.save(obra);
    }

    private void validarObraJaCadastrada(Obra obra) {
        obraRepository.findByNome(obra.getNome())
            .ifPresent(obraExistente -> {
                if (obra.isNovoCadastro() || !obra.getId().equals(obraExistente.getId())) {
                    throw OBRA_JA_EXISTENTE.getException();
                }
            });
    }

    public Obra buscarUm(Integer id, Integer clienteId) {
        var cliente = clienteService.buscarUm(clienteId);
        return obraRepository.findByIdAndClienteId(id, cliente.getId())
            .orElseThrow(OBRA_NAO_CADASTRADA::getException);
    }

    public List<Obra> buscarTodos(Integer clienteId) {
        return obraRepository.findByClienteId(clienteService.buscarUm(clienteId).getId());
    }
}
