package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.moduloaplicativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public List<Modulo> buscarTodos() {
        return moduloRepository.findAll();
    }

}
