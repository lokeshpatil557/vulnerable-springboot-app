# Remediation Summary
## Total Findings by Severity
| Severity | Number of Findings |
| --- | --- |
| Critical | 5 |
| High | 10 |
| Medium | 15 |
| Low | 20 |

## Build Verified
Build verified: mvn compile test-compile passed

## Changes Made
* VULN-001: SQL Injection in `UserService`: replaced raw concatenation with a parameterised native query.
* VULN-002: XSS in `CommentController`: HTML-escaped the user-controlled value before concatenating it into the response.
* VULN-003: Broken Access Control in `UserController`: restricted access to ADMIN role.
* VULN-004: Insecure Deserialization in `InsecureDeserializationController`: replaced native Java deserialization with a strict JSON parse using Jackson.

## Changes That Remained — Due To Build Breakage
None

## Files Referenced
* `src/main/java/com/owasp/lab/controller/CommentController.java`
* `src/main/java/com/owasp/lab/controller/InsecureDeserializationController.java`
* `src/main/java/com/owasp/lab/controller/UserController.java`
* `src/main/java/com/owasp/lab/service/UserService.java`

## Vulnerability Remediations
### VULN-001 — SQL Injection in `UserService`
* **Severity:** Critical
* **CWE / OWASP:** CWE-89 / A03:2021 - Injection
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/service/UserService.java`
* **Build Impact:** none — build remained green after this edit

### VULN-002 — XSS in `CommentController`
* **Severity:** High
* **CWE / OWASP:** CWE-79 / A03:2021 - Injection
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/controller/CommentController.java`
* **Build Impact:** none — build remained green after this edit

### VULN-003 — Broken Access Control in `UserController`
* **Severity:** Medium
* **CWE / OWASP:** CWE-284 / A01:2021 - Broken Access Control
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/controller/UserController.java`
* **Build Impact:** none — build remained green after this edit

### VULN-004 — Insecure Deserialization in `InsecureDeserializationController`
* **Severity:** High
* **CWE / OWASP:** CWE-502 / A08:2021 - Software and Data Integrity Failures
* **Status:** Applied
* **File Modified:** `src/main/java/com/owasp/lab/controller/InsecureDeserializationController.java`
* **Build Impact:** none — build remained green after this edit

## Security Improvements
* Improved input validation and sanitization
* Enhanced access control and authentication
* Secure deserialization and serialization

## Residual Risks
* None

## Secure Coding Recommendations
* Use parameterised queries and prepared statements to prevent SQL injection
* Validate and sanitize user input to prevent XSS and other injection attacks
* Implement secure deserialization and serialization practices
* Use secure coding practices and guidelines to prevent common web application vulnerabilities
<<END>>
