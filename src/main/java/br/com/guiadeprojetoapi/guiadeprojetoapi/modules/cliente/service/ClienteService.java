package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.config.exception.ValidacaoException;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto.ClienteRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Cliente;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.repository.ClienteRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.exception.ClienteException.CLIENTE_CPF_JA_CADASTRADO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.exception.ClienteException.CLIENTE_NOME_JA_CADASTRADO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Cliente.of;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UsuarioService usuarioService;

    public void salvar(ClienteRequest request) {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        var cliente = of(request, usuarioLogado.getId());
        validarDadosDoCliente(cliente);
        clienteRepository.save(cliente);
    }

    private void validarDadosDoCliente(Cliente cliente) {
        validarCpfExistente(cliente);
        validarNomeExistente(cliente);
    }

    private void validarCpfExistente(Cliente cliente) {
        clienteRepository.findByCpf(cliente.getCpf())
            .ifPresent(clienteExistente -> {
                if (cliente.isNovoCadastro() || !cliente.getId().equals(clienteExistente.getId())) {
                    throw CLIENTE_CPF_JA_CADASTRADO.getException();
                }
            });
    }

    private void validarNomeExistente(Cliente cliente) {
        clienteRepository.findByNome(cliente.getNome())
            .ifPresent(clienteExistente -> {
                if (cliente.isNovoCadastro() || !cliente.getId().equals(clienteExistente.getId())) {
                    throw CLIENTE_NOME_JA_CADASTRADO.getException();
                }
            });
    }

    public List<Cliente> buscarTodos() {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        return clienteRepository.findByUsuarioId(usuarioLogado.getId());
    }

    public Cliente buscarUm(Integer id) {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        return clienteRepository.findByIdAndUsuarioId(id, usuarioLogado.getId())
            .orElseThrow(() -> new ValidacaoException("O cliente não foi encontrado."));
    }
}
