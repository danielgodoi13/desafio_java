package com.daniel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.context.event.EventListener;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/css/**").permitAll() // Permitir acesso à página de login e aos arquivos CSS sem autenticação
                .antMatchers("/api/**").hasRole("USER") // Proteger os endpoints da API
                .anyRequest().authenticated() // Requer autenticação para qualquer outra solicitação
                .and()
                .formLogin()
                .loginPage("/login") // Definir a página de login personalizada
                .defaultSuccessUrl("/home", true) // Redirecionar para a página principal após o login bem-sucedido
                .failureUrl("/login?error=true") // Redirecionar para a página de login com erro
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic() // Autenticação básica para endpoints da API
                .and()
                .csrf().disable(); // Desabilitar CSRF para facilitar os testes
    }

    @Bean
    @Override
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("unoesc")
                .password("desafio")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        logger.info("Login bem-sucedido para o usuário: {}", username);
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        logger.warn("Falha no login para o usuário: {}", username);
    }
}
