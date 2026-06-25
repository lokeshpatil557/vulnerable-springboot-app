# Remediation Summary
## Total Findings by Severity:
* Critical: 1
* High: 0
* Medium: 0
* Low: 0
## Build verified: mvn compile test-compile passed

# Changes Made
* VULN-001 — `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via `:username`. User input is treated as a literal value by Hibernate and can never alter the SQL structure.

# Changes That Remained — Due To Build Breakage
None

# Files Referenced
* `src/main/java/com/owasp/lab/service/UserService.java`: replaced raw concatenation with a parameterised native query bound via `:username`.

# Vulnerability Remediations
### VULN-001 — SQL Injection (Critical)
* **Severity:** Critical
* **CWE / OWASP:** CWE-89 / A03:2021 - Injection
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/service/UserService.java`
* **Build Impact:** none — build remained green after this edit

**1. Original Vulnerable Code**
<<END>>


> **Build verified: mvn compile test-compile passed** (recorded by the workflow after the agent emitted an empty patch set, or after patches applied cleanly).
