package com.backend.ecommerce.shared.security;

import com.backend.ecommerce.application.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final AuthFilter authFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthFilter authFilter) {
        this.userDetailsService = userDetailsService;
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers(HttpMethod.GET, "/api/products/**", "/api/categories/**")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/users/register", "/api/users/login")
                                .permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/products/**", "/api/categories/**")
                                .hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/products/**", "/api/categories/**")
                                .hasAuthority("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/products/**", "/api/categories/**")
                                .hasAuthority("ADMIN")
                                .requestMatchers("/api/users/**").hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated())
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
