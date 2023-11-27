package com.spring.fotoAlbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    // configurazione su come avere uno UserDetailsService
    @Bean
    DataBaseUserDetailsService userDetailsService() {
        return new DataBaseUserDetailsService();
    }

    // configurazione su come avere uno PasswordEncoderFactories
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // configurazione delll'AuthenticationProvider
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        // Creo un DaoAuthenticationProvider
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //gli assegno lo UserDetailsService
        provider.setUserDetailsService(userDetailsService());
        // gli assegno una password
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // SecurityFilterChain che fa da "dogana"
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/categories").hasAuthority("ADMIN")
                .requestMatchers("/users").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/photos/**").hasAuthority("ADMIN")
                .requestMatchers("/photos", "/photos/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        http.csrf().disable();
        return http.build();
    }

}
