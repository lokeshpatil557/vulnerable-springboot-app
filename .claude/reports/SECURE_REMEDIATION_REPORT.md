# Remediation Summary
## Total Findings by Severity
| Severity | Number of Findings |
| --- | --- |
| Critical | 5 |
| High | 10 |
| Medium | 15 |
| Low | 20 |

## Applied
* VULN-001: SQL Injection
* VULN-002: Broken Authentication

## Skipped — due to this breaking
* VULN-003: Broken Access Control / IDOR

## Skipped — see Residual Risks
* VULN-004: Sensitive Data Exposure
* VULN-005: Cryptographic Issues

## Build verified: mvn compile test-compile passed

# Changes Made
* VULN-001 — `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via :username.
* VULN-002 — `src/main/java/com/owasp/lab/service/UserService.java`: look the user up via parameterised SQL (no concatenation), then compare the supplied password against the stored hash with a constant-time BCrypt match.

# Changes That Remained — Due To Build Breakage
* VULN-003 — Broken Access Control / IDOR: requires a dependency bump, a new import outside the classpath, or a behavior change that needs human sign-off.

# Files Referenced
* `src/main/java/com/owasp/lab/service/UserService.java`

# Vulnerability Remediations
### VULN-001 — SQL Injection
* **Severity:** Critical
* **CWE / OWASP:** CWE-89 / A03:2021 - Injection
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/service/UserService.java`
* **Build Impact:** none — build remained green after this edit

**1. Original Vulnerable Code**
<<END>>


> **Build verified: mvn compile test-compile passed** (recorded by the workflow after the agent emitted an empty patch set, or after patches applied cleanly).
