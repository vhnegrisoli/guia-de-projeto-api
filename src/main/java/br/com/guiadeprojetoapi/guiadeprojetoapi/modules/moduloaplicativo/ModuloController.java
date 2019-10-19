package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.moduloaplicativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/modulos")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping
    public List<Modulo> buscarTodos() {
        return moduloService.buscarTodos();
    }
}
