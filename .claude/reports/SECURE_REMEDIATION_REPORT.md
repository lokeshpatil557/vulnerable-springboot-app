# Remediation Summary
## Total Findings by Severity:
| Severity | Count |
| --- | --- |
| Critical | 5 |
| High | 10 |
| Medium | 5 |
| Low | 2 |

## Applied count: 1
## Skipped — due to this breaking count: 0
## Skipped — see Residual Risks count: 0
## Breakdown by severity:
| Severity | Applied | Skipped — due to this breaking | Skipped — see Residual Risks |
| --- | --- | --- | --- |
| Critical | 1 | 0 | 0 |
| High | 0 | 0 | 0 |
| Medium | 0 | 0 | 0 |
| Low | 0 | 0 | 0 |
## Headline outcome: Build verified: mvn compile test-compile passed

# Changes Made
* VULN-001 — `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via :username.

# Changes That Remained — Due To Build Breakage
None

# Files Referenced
* `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via :username.

# Vulnerability Remediations
### VULN-001 — SQL Injection
- **Severity:** Critical
- **CWE / OWASP:** CWE-89 / A03:2021 - Injection
- **Status:** Applied
- **File Modified:** `src/main/java/com/owasp/lab/service/UserService.java`
- **Build Impact:** none — build remained green after this edit

**1. Original Vulnerable Code**
<<END>>
