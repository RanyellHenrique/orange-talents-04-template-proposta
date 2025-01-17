package br.com.zupperacademy.ranyell.proposta.compartilhado.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAnyAuthority("ROLE_CLIENT", "ROLE_ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/cartoes/**").hasAnyAuthority("ROLE_CLIENT", "ROLE_ADMIN")
                        .antMatchers(HttpMethod.POST, "/api/propostas/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CLIENT")
                        .antMatchers(HttpMethod.GET, "/api/propostas/**").hasAnyAuthority("ROLE_CLIENT", "ROLE_ADMIN")
                        .antMatchers(HttpMethod.GET, "/actuator/prometheus").permitAll()
                        .antMatchers(HttpMethod.GET, "/actuator/helth/**").hasAnyAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                        .oauth2ResourceServer()
                        .jwt()
                        .jwtAuthenticationConverter(getJwtAuthenticationConverter());
    }

    /*
    * Define que os Roles serão considerados para o acesso aos endpoits
    * */
    JwtAuthenticationConverter getJwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("authorities");
        converter.setAuthorityPrefix("");
        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
        return authenticationConverter;
    }

}