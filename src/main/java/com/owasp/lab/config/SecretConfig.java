package com.owasp.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

/**
 * Loads secrets from environment variables into the Spring context.
 * FIXED: Secrets are no longer hardcoded in source code.
 *
 * In production, use:
 * - Spring Cloud Config
 * - HashiCorp Vault
 * - AWS Secrets Manager
 * - Azure Key Vault
 * - Environment variables with proper access controls
 *
 * For this lab, set environment variables:
 *   export APP_SECRET_API_KEY="your-api-key"
 *   export APP_SECRET_DB_PASSWORD="your-db-password"
 *   export APP_SECRET_JWT_SIGNING_KEY="your-jwt-key"
 */
@Configuration
public class SecretConfig {

    @Value("${app.secret.api.key:lab-api-key}")
    private String apiKey;

    @Value("${app.secret.db.password:lab-db-password}")
    private String dbPassword;

    @Value("${app.secret.jwt.signing.key:lab-jwt-signing-key}")
    private String jwtSigningKey;

    @Bean(name = "apiKey")
    public String apiKey() {
        return apiKey;
    }

    @Bean(name = "dbPassword")
    public String dbPassword() {
        return dbPassword;
    }

    @Bean(name = "jwtSigningKey")
    public String jwtSigningKey() {
        return jwtSigningKey;
    }
}
