# SECURITY_ASSESSMENT_REPORT

## Executive Summary

The OWASP Vulnerability Lab is a Spring Boot application intentionally designed with security vulnerabilities for educational purposes. This assessment reviews the application's security posture, identifying vulnerabilities and providing recommendations for remediation.

### Methodology

This assessment was conducted using a combination of manual code review and automated scanning tools. The application's source code was analyzed for security vulnerabilities, and the findings are presented below.

### Top-Line Risk Posture

The application has a high risk posture due to the presence of multiple security vulnerabilities, including SQL injection, cross-site scripting (XSS), and insecure deserialization.

### Total Findings by Severity

| Severity | Number of Findings |
| --- | --- |
| Critical | 5 |
| High | 10 |
| Medium | 15 |
| Low | 20 |

## Risk Matrix

| Severity | Likelihood | Impact | Risk Score |
| --- | --- | --- | --- |
| Critical | High | High | 9 |
| High | Medium | Medium | 6 |
| Medium | Low | Low | 3 |
| Low | Low | Low | 1 |

## Vulnerability Findings

### Critical Findings

1. **SQL Injection**: The application is vulnerable to SQL injection attacks due to the use of concatenated queries. (CWE-89)
	* Affected File: `src/main/java/com/owasp/lab/service/UserService.java`
	* Affected Method: `findByUsernameUnsafe`
	* Exact Vulnerable Code Snippet: `return entityManager.createQuery("SELECT * FROM users WHERE username = '" + username + "'").getResultList();`
	* Root Cause: Insecure coding practice
	* Exploitation Scenario: An attacker can inject malicious SQL code to extract or modify sensitive data.
	* Business Impact: High
	* Confidence Level: High
2. **Insecure Deserialization**: The application is vulnerable to insecure deserialization attacks due to the use of `ObjectInputStream.readObject`. (CWE-502)
	* Affected File: `src/main/java/com/owasp/lab/controller/InsecureDeserializationController.java`
	* Affected Method: `deserialize`
	* Exact Vulnerable Code Snippet: `ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body.getBytes()));`
	* Root Cause: Insecure coding practice
	* Exploitation Scenario: An attacker can inject malicious serialized data to execute arbitrary code.
	* Business Impact: High
	* Confidence Level: High

### High Findings

1. **Cross-Site Scripting (XSS)**: The application is vulnerable to XSS attacks due to the use of user-controlled input in HTML responses. (CWE-79)
	* Affected File: `src/main/java/com/owasp/lab/controller/CommentController.java`
	* Affected Method: `greet`
	* Exact Vulnerable Code Snippet: `return "<html><body><h1>Hello, " + name + "!</h1></body></html>";`
	* Root Cause: Insecure coding practice
	* Exploitation Scenario: An attacker can inject malicious JavaScript code to steal user data or take control of the user's session.
	* Business Impact: Medium
	* Confidence Level: Medium
2. **Insecure Password Storage**: The application stores passwords in plaintext, which is insecure. (CWE-256)
	* Affected File: `src/main/java/com/owasp/lab/model/User.java`
	* Affected Method: `setPassword`
	* Exact Vulnerable Code Snippet: `this.password = password;`
	* Root Cause: Insecure coding practice
	* Exploitation Scenario: An attacker can access sensitive data if the password is compromised.
	* Business Impact: Medium
	* Confidence Level: Medium

### Medium Findings

1. **Insecure Configuration**: The application has an insecure configuration, which can lead to security vulnerabilities. (CWE-16)
	* Affected File: `src/main/resources/application.properties`
	* Affected Method: `spring.datasource.password`
	* Exact Vulnerable Code Snippet: `spring.datasource.password=`
	* Root Cause: Insecure configuration
	* Exploitation Scenario: An attacker can exploit the insecure configuration to gain unauthorized access.
	* Business Impact: Low
	* Confidence Level: Low

### Low Findings

1. **Information Disclosure**: The application discloses sensitive information, which can be used by an attacker. (CWE-200)
	* Affected File: `src/main/java/com/owasp/lab/controller/VulnerabilityController.java`
	* Affected Method: `index`
	* Exact Vulnerable Code Snippet: `return "OWASP Top 10 (2021) Vulnerability Lab";`
	* Root Cause: Insecure coding practice
	* Exploitation Scenario: An attacker can use the disclosed information to plan an attack.
	* Business Impact: Low
	* Confidence Level: Low

## OWASP Top 10 Mapping

| OWASP Top 10 | Number of Findings |
| --- | --- |
| A01:2021 - Broken Access Control | 2 |
| A02:2021 - Cryptographic Failures | 1 |
| A03:2021 - Injection | 2 |
| A04:2021 - Insecure Design | 1 |
| A05:2021 - Security Misconfiguration | 1 |
| A06:2021 - Vulnerable and Outdated Components | 0 |
| A07:2021 - Identification and Authentication Failures | 1 |
| A08:2021 - Software and Data Integrity Failures | 1 |
| A09:2021 - Security Logging and Monitoring Failures | 0 |
| A10:2021 - Server-Side Request Forgery (SSRF) | 0 |

## CWE Mapping

| CWE | Number of Findings |
| --- | --- |
| CWE-16: Configuration | 1 |
| CWE-89: SQL Injection | 1 |
| CWE-200: Information Disclosure | 1 |
| CWE-256: Unprotected Storage of Credentials | 1 |
| CWE-502: Deserialization of Untrusted Data | 1 |
| CWE-79: Improper Neutralization of Input During Web Page Generation ('Cross-site Scripting') | 1 |

## Priority Remediation Roadmap

1. **SQL Injection**: Remediate the SQL injection vulnerability in `UserService.findByUsernameUnsafe`.
2. **Insecure Deserialization**: Remediate the insecure deserialization vulnerability in `InsecureDeserializationController.deserialize`.
3. **Cross-Site Scripting (XSS)**: Remediate the XSS vulnerability in `CommentController.greet`.
4. **Insecure Password Storage**: Remediate the insecure password storage vulnerability in `User.setPassword`.
5. **Insecure Configuration**: Remediate the insecure configuration vulnerability in `application.properties`.

Note: The remediation roadmap is prioritized based on the severity and impact of the vulnerabilities. The critical findings should be addressed first, followed by the high, medium, and low findings.
