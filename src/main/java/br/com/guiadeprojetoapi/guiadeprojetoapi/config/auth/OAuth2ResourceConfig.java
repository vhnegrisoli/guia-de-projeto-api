package br.com.guiadeprojetoapi.guiadeprojetoapi.config.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import java.util.List;

import static br.com.guiadeprojetoapi.guiadeprojetoapi.modules.usuario.enums.EPermissao.*;
import static java.util.Arrays.asList;

@Configuration
@EnableResourceServer
public class OAuth2ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${app-config.oauth-clients.guia-de-projeto-api.client}")
    private String oauthClient;
    @Value("${app-config.oauth-clients.guia-de-projeto-api.secret}")
    private String oauthClientSecret;
    @Value("${app-config.services.biot-admin.url}")
    private String oauthServerUrl;

    @Override
    @SuppressWarnings({"checkstyle:methodlength"})
    public void configure(HttpSecurity http) throws Exception {
        String[] permitAll = {
            "/login/**",
            "/oauth/token",
            "/oauth/authorize",
            "/api/usuarios/novo",
            "/api/clientes/endereco/**",
        };

        http
            .addFilterBefore(new CorsConfigFilter(), ChannelProcessingFilter.class)
            .requestMatchers()
            .antMatchers("/**")
            .and()
            .authorizeRequests()
            .antMatchers(permitAll).permitAll()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/api/usuarios/all").hasAnyRole(APPLICATION.name())
            .antMatchers("/api/usuarios/**").hasAnyRole(ADMIN.name(), USER.name(), APPLICATION.name())
            .antMatchers("/api/zona-residencial/**").hasAnyRole(ADMIN.name(), USER.name(), APPLICATION.name());
    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());
    }

    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(oauthServerUrl + "/oauth/check_token");
        tokenService.setClientId(oauthClient);
        tokenService.setClientSecret(oauthClientSecret);
        return tokenService;
    }
}
