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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.exception.UsuarioException.*;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.model.Usuario.of;
import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.dto.UsuarioAutenticado.of;

@Service
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    public void save(UsuarioRequest usuarioRequest) {
        var usuario = of(usuarioRequest);
        validarDadosUsuario(usuario);
        usuario.setSenha(bcryptPasswordEncoder.encode(usuarioRequest.getSenha()));
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setUltimoAcesso(LocalDateTime.now());
        usuarioRepository.save(usuario);
    }

    private void validarDadosUsuario(Usuario usuario) {
        validarEmailExistente(usuario);
        validarCpfExistente(usuario);
    }

    private void validarEmailExistente(Usuario usuario) {
        usuarioRepository.findByEmail(usuario.getEmail())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_EMAIL_JA_CADASTRADO.getException();
                }
            });
    }

    private void validarCpfExistente(Usuario usuario) {
        usuarioRepository.findByCpf(usuario.getCpf())
            .ifPresent(usuarioExistente -> {
                if (usuario.isNovoCadastro() || !usuario.getId().equals(usuarioExistente.getId())) {
                    throw USUARIO_CPF_JA_CADASTRADO.getException();
                }
            });
    }

    @Transactional
    public UsuarioAutenticado getUsuarioAutenticadoAtualizaUltimaData() {
        var usuarioAtualizado = usuarioRepository
            .findById(getUsuarioAutenticado().getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException);
        return of(atualizarUltimoAcesso(usuarioAtualizado));
    }

    @Transactional
    private Usuario atualizarUltimoAcesso(Usuario usuario) {
        usuario.setUltimoAcesso(LocalDateTime.now());
        return usuarioRepository.save(usuario);
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
        return of(usuarioRepository.findByEmail(email).orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }

    public List<Usuario> getUsuarios() {
        var usuarioAutenticado = getUsuarioAutenticado();
        if (usuarioAutenticado.isAdmin()) {
            return usuarioRepository.findAll();
        }
        return List.of(usuarioRepository.findById(usuarioAutenticado.getId())
            .orElseThrow(USUARIO_NAO_ENCONTRADO::getException));
    }

    public UsuarioAutenticado findUsuarioAutenticadoByEmail() {
        return usuarioRepository.findUsuarioAutenticadoByEmail(getEmail());
    }

    private String getEmail() {
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
        return email;
    }
}
