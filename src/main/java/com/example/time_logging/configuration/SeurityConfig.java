package com.example.time_logging.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable()) // onemogućava CSRF zaštitu
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/users").permitAll() // omogući pristup ovoj ruti bez autentifikacije
                        .anyRequest().authenticated()
                );
        return httpSecurity.build();
    }


}
