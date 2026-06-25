# SECURITY_ASSESSMENT_REPORT

## Executive Summary

The OWASP Vulnerability Lab is a Spring Boot application intentionally designed to be insecure for educational purposes. This report summarizes the findings of a comprehensive static application security review of the entire codebase.

**Scope:** The review covered all Java source files under `src/main/`, `pom.xml` for dependency and configuration risks, and `src/main/resources/application*.{yml,yaml,properties}` for misconfiguration.

**Methodology:** The review analyzed the code for security vulnerabilities, insecure coding practices, OWASP Top 10 issues, sensitive data exposure, dependency risks, broken authentication/authorization, insecure API implementations, and configuration weaknesses.

**Top-line Risk Posture:** The application has a high risk posture due to the presence of multiple critical and high-severity vulnerabilities.

**Total Findings by Severity:**

* Critical: 5
* High: 10
* Medium: 5
* Low: 2

## Risk Matrix

| Severity | Likelihood | Count |
| --- | --- | --- |
| Critical | High | 5 |
| High | Medium | 10 |
| Medium | Low | 5 |
| Low | Low | 2 |

## Vulnerability Findings

### VULN-001: SQL Injection (Critical)

* Vulnerability Name: SQL Injection
* CWE ID: CWE-89
* OWASP Top 10 Category: A03:2021 - Injection
* Severity: Critical
* Affected File: `src/main/java/com/owasp/lab/service/UserService.java`
* Affected Method/Class: `findByUsernameUnsafe`
* Exact Vulnerable Code Snippet:
