# Remediation Summary
## Total Findings: 1
## Applied: 1
## Skipped — due to this breaking: 0
## Skipped — see Residual Risks: 0
## Breakdown by Severity:
### Critical: 1
### High: 0
### Medium: 0
### Low: 0
## Headline Outcome: Build verified: mvn compile test-compile passed

# Changes Made
* VULN-001 — `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via `:username`. User input is treated as a literal value by Hibernate and can never alter the SQL structure.

# Changes That Remained — Due To Build Breakage
None

# Files Referenced
* `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via `:username`.

# Vulnerability Remediations
### VULN-001 — SQL Injection
#### Severity: Critical
#### CWE / OWASP: CWE-89 / A03:2021 - Injection
#### Status: Applied
#### File Modified: `src/main/java/com/owasp/lab/service/UserService.java`
#### Build Impact: none — build remained green after this edit

#### 1. Original Vulnerable Code
<<END>>
