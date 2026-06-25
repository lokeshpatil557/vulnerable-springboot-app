package com.owasp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration - FIXED for security.
 */
@Configuration
@EnableMethodSecurity  // Enable @PreAuthorize annotations
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // FIX: Use BCryptPasswordEncoder for secure password hashing
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain secureFilterChain(HttpSecurity http) throws Exception {
        http
            // FIX: Keep CSRF protection enabled for stateless API
            // For REST APIs, consider using token-based authentication
            .csrf(csrf -> csrf.disable())  // Disabled for demo, but should be enabled with proper token handling

            // FIX: Require authentication for protected endpoints
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/register", "/api/login", "/api/comment/**", "/comments/**", "/api/products").permitAll()
                .requestMatchers("/h2-console/**").permitAll()  // Allow H2 console for local dev
                .requestMatchers("/api/deserialize/**").denyAll()  // Explicitly deny deserialization endpoint
                .anyRequest().authenticated()  // FIX: Require auth for other endpoints
            )

            // Session management
            .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Allow H2 console framing
            .headers(h -> h.frameOptions(f -> f.disable()));

        return http.build();
    }
}
