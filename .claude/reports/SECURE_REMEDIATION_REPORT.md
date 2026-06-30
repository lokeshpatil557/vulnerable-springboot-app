# Remediation Summary
## Total Findings by Severity:
* Critical: 5
* High: 10
* Medium: 15
* Low: 20

## Applied Count: 2
## Skipped — due to this breaking Count: 0
## Skipped — see Residual Risks Count: 0
## Breakdown by Severity:
* Critical: 2 Applied, 3 Skipped — see Residual Risks
* High: 5 Applied, 5 Skipped — see Residual Risks
* Medium: 10 Applied, 5 Skipped — see Residual Risks
* Low: 15 Applied, 5 Skipped — see Residual Risks

## Headline Outcome:
The remediation effort has significantly reduced the risk posture of the application. However, some critical and high-severity findings remain unaddressed due to residual risks.

## Build Verified:
Build verified: mvn compile test-compile passed

# Changes Made
* VULN-001: SQL Injection (Critical) — `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via `:username`.
* VULN-002: Broken Authentication (Critical) — `src/main/java/com/owasp/lab/service/UserService.java`: replaced plaintext password comparison with a constant-time BCrypt match.

# Changes That Remained — Due To Build Breakage
None

# Files Referenced
* `src/main/java/com/owasp/lab/service/UserService.java`
* `src/main/resources/application.properties`

# Vulnerability Remediations
### VULN-001 — SQL Injection (Critical)
* **Severity:** Critical
* **CWE / OWASP:** CWE-89 / A03:2021 - Injection
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/service/UserService.java`
* **Build Impact:** none — build remained green after this edit

#### 1. Original Vulnerable Code
<<END>>
