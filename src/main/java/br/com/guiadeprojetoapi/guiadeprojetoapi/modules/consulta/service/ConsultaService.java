package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.dto.ConsultaResponse;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.repository.ConsultaRepository;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.service.UsuarioService;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.repository.InscricaoImobiliariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.exception.ConsultaException.CONSULTA_LIMITE_ATINGIDO;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.consulta.model.Consulta.of;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.zonaresidencial.exception.ZonaResidencialException.INSCRICAO_NAO_ENCONTRADA;

@Service
public class ConsultaService {

    private static final Integer MAXIMO_CONSULTAS = 10;

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private InscricaoImobiliariaRepository inscricaoImobiliariaRepository;

    public void save(ConsultaRequest request) {
        var usuarioLogado = usuarioService.getUsuarioAutenticado();
        var consulta = of(request, usuarioLogado);
        consulta.setInscricaoImobiliaria(inscricaoImobiliariaRepository
            .findByCodigoInscricao(request.getCodigoInscricao())
            .orElseThrow(INSCRICAO_NAO_ENCONTRADA::getException));
        validarQuantidadeDeConsultas(usuarioLogado.getId());
        consultaRepository.save(consulta);
    }

    private void validarQuantidadeDeConsultas(Integer usuarioId) {
        var consultas = consultaRepository.findByUsuarioId(usuarioId);
        if (consultas.size() >= MAXIMO_CONSULTAS) {
            throw CONSULTA_LIMITE_ATINGIDO.getException();
        }
    }

    public List<ConsultaResponse> buscarConsultas() {
        return consultaRepository
            .findByUsuarioId(usuarioService.getUsuarioAutenticado().getId())
            .stream()
            .map(ConsultaResponse::of)
            .collect(Collectors.toList());
    }
}
