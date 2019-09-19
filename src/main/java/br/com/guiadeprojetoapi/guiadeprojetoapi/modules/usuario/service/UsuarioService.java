package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.service;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioRequest;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario;
import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.exception.UsuarioException.*;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario.of;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    public void save(UsuarioRequest usuarioRequest) {
        validarDadosUsuario(usuarioRequest);
        var usuario = of(usuarioRequest);
        usuario.setSenha(bcryptPasswordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    private void validarDadosUsuario(UsuarioRequest usuarioRequest) {
        validarEmailExistente(usuarioRequest.getEmail());
        validarCpfExistente(usuarioRequest.getCpf());
    }

    private void validarEmailExistente(String email) {
        usuarioRepository.findByEmail(email)
            .ifPresent(usuarioExistente -> {
                throw USUARIO_EMAIL_JA_CADASTRADO.getException();
            });
    }

    private void validarCpfExistente(String cpf) {
        usuarioRepository.findByCpf(cpf)
            .ifPresent(usuarioExistente -> {
                throw USUARIO_CPF_JA_CADASTRADO.getException();
            });
    }

    public UsuarioAutenticado getUsuarioAutenticado() {
        var email = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (principal instanceof UserDetails) {
                email = ((UserDetails)principal).getUsername();
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw USUARIO_SEM_SESSAO.getException();
        }
        return UsuarioAutenticado.of(usuarioRepository.findByEmail(email).orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }

    public List<Usuario> getUsuarios() {
        var usuarioAutenticado = getUsuarioAutenticado();
        if (usuarioAutenticado.isAdmin()) {
            return usuarioRepository.findAll();
        }
        return List.of(usuarioRepository.findById(usuarioAutenticado.getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }

    public void atualizarUltimoAcesso() {
        usuarioRepository.findById(getUsuarioAutenticado().getId())
            .ifPresent(usuario -> {
                usuario.setUltimoAcesso(LocalDateTime.now());
                usuarioRepository.save(usuario);
            });
    }
}
