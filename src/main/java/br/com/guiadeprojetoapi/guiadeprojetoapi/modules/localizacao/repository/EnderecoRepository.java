package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.repository;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.localizacao.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    List<Endereco> findByCep(String cep);
}
