package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.dto.EnderecoRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Endereço inserido com sucesso!")
    public void save(@RequestBody EnderecoRequest request) {
        enderecoService.save(request);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.CREATED, reason = "Endereço alterado com sucesso!")
    public void alterar(@RequestBody EnderecoRequest request) {
        enderecoService.save(request);
    }

    @GetMapping("{id}")
    public Endereco buscar(@PathVariable Integer id) {
        return enderecoService.buscar(id);
    }
}
