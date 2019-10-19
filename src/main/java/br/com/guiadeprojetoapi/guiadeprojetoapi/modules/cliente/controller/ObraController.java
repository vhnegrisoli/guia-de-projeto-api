package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.controller;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.dto.ObraRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.model.Obra;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.cliente.service.ObraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obras")
public class ObraController {

    @Autowired
    private ObraService obraService;

    @PostMapping("/nova")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Obra cadastrada com sucesso!")
    public void save(@RequestBody ObraRequest request) {
        obraService.save(request);
    }

    @PutMapping("/alterar")
    @ResponseStatus(value = HttpStatus.OK, reason = "Obra alterada com sucesso!")
    public void alterar(@RequestBody ObraRequest request) {
        obraService.save(request);
    }

    @GetMapping("{id}/cliente/{clienteId}")
    public Obra buscarUma(@PathVariable Integer id, @PathVariable Integer clienteId) {
        return obraService.buscarUm(id, clienteId);
    }

    @GetMapping("{clienteId}")
    public List<Obra> buscarTodas(@PathVariable Integer clienteId) {
        return obraService.buscarTodos(clienteId);
    }
}
