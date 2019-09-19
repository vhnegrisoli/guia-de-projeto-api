package br.com.guiadeprojetoapi.guiadeprojetoapi.config.auth;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.exception.UsuarioException.USUARIO_ACESSO_INVALIDO;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return usuarioRepository
            .findByEmail(email)
            .map(usuario -> {
                List<GrantedAuthority> permissoes = AuthorityUtils
                    .createAuthorityList("ROLE_" + usuario.getPermissao().getPermissao().name());
                return new User(
                    usuario.getEmail(),
                    usuario.getSenha(),
                    permissoes);
            }).orElseThrow(USUARIO_ACESSO_INVALIDO::getException);
    }
}