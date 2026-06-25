# SECURITY_ASSESSMENT_REPORT

## Executive Summary

The OWASP Vulnerability Lab is a Spring Boot application intentionally designed to be insecure for educational purposes. This report summarizes the findings of a comprehensive static application security review.

**Methodology:**

1. Analyzed all Java source files under `src/main/`.
2. Analyzed `pom.xml` for dependency and configuration risks.
3. Analyzed `src/main/resources/application*.{yml,yaml,properties}` for misconfiguration.
4. Identified security vulnerabilities, insecure coding practices, OWASP Top 10 issues, sensitive data exposure, dependency risks, broken authentication/authorization, insecure API implementations, and configuration weaknesses.

**Top-line Risk Posture:**

The application has a high risk posture due to the presence of multiple critical and high-severity vulnerabilities.

**Total Findings by Severity:**

| Severity | Count |
| --- | --- |
| Critical | 5 |
| High | 10 |
| Medium | 5 |
| Low | 2 |

## Risk Matrix

| Severity | Likelihood | Count |
| --- | --- | --- |
| Critical | High | 3 |
| Critical | Medium | 2 |
| High | High | 5 |
| High | Medium | 3 |
| Medium | High | 2 |
| Medium | Medium | 3 |
| Low | Low | 2 |

## Vulnerability Findings

### VULN-001: SQL Injection (Critical)

* **Vulnerability Name:** SQL Injection
* **CWE ID:** CWE-89
* **OWASP Top 10 Category:** A03:2021 - Injection
* **Severity:** Critical
* **Affected File:** `src/main/java/com/owasp/lab/service/UserService.java`
* **Affected Method/Class:** `findByUsernameUnsafe`
* **Exact Vulnerable Code Snippet:**
