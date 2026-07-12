# Executive Summary
## Scope
This security assessment reviewed the entire Spring Boot codebase of the OWASP Top 10 (2021) Vulnerability Lab, including all Java source files under `src/main/`, `pom.xml`, and `src/main/resources/application*.{yml,yaml,properties}`.

## Methodology
The assessment applied the following check categories:
- Injection (SQL Injection, NoSQL Injection, Command Injection, Expression Injection)
- Cross-Site Scripting (Reflected XSS, Stored XSS, DOM XSS)
- Authentication (Plaintext passwords, Missing password hashing, Weak session management, Broken authentication flows)
- Authorization (IDOR, Missing `@PreAuthorize` / `@Secured`, Privilege escalation, `.permitAll()` on sensitive endpoints)
- Security Misconfiguration (Hardcoded secrets / credentials in source or `application.yml` / `application.properties`, Debug endpoints enabled, Verbose error messages / stack traces leaked, CORS misconfiguration, Actuator endpoints exposed without auth)
- Sensitive Data Exposure (API keys, tokens, passwords, secrets, PII in source or config)
- Cryptographic Issues (Weak hashing: MD5, SHA-1, plain SHA-256 for passwords, Insecure RNG, Hardcoded IVs / static salts, Disabled TLS)
- Deserialization (Unsafe Java deserialization, Jackson default typing with `@JsonTypeInfo` on untrusted input)
- File Handling (Path traversal, Arbitrary file read/write, File upload without validation)
- API Security (Missing `@Valid` / input validation, Missing rate limiting, Missing authentication on sensitive endpoints, Missing CSRF protection on state-changing endpoints)
- Spring Security Specific ( `csrf().disable()` on session-based apps, `authorizeRequests().anyRequest().permitAll()`, Anonymous access to privileged resources, Form login over HTTP)
- Dependency Risks (Review `pom.xml`, Flag known-vulnerable library versions, Flag unmaintained dependencies)

## Risk Posture
The OWASP Top 10 (2021) Vulnerability Lab is intentionally insecure and should not be deployed to any public server. The assessment identified several critical, high, medium, and low-severity findings, indicating a high risk posture. The lab's insecure design and implementation make it vulnerable to various attacks, including injection, cross-site scripting, authentication and authorization bypasses, and sensitive data exposure.

## Finding Counts by Severity
- Critical: 1
- High: 2
- Medium: 2
- Low: 1
Total findings: 6
