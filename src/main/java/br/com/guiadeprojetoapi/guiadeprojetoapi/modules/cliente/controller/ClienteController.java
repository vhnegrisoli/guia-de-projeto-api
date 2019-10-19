package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto.ClienteRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Cliente;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/novo")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Cliente cadastrado com sucesso!")
    public void save(@RequestBody ClienteRequest request) {
        clienteService.salvar(request);
    }

    @PutMapping("/alterar-acesso")
    @ResponseStatus(value = HttpStatus.OK, reason = "Cliente alterado com sucesso!")
    public void alterarCliente(@RequestBody ClienteRequest request) {
        clienteService.salvar(request);
    }

    @GetMapping("{id}")
    public Cliente buscarCliente(@PathVariable Integer id) {
        return clienteService.buscarUm(id);
    }

    @GetMapping
    public List<Cliente> buscarTodos() {
        return clienteService.buscarTodos();
    }
}
