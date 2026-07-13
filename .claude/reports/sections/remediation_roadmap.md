# Priority Remediation Roadmap

1. **VULN-001**: SQL Injection in `UserService.findByUsernameUnsafe` (Critical)
	* Remediation: Use parameterized queries instead of string concatenation to prevent SQL injection attacks.
	* Fix: Replace the vulnerable code with a parameterized query using `@Query` annotation or `EntityManager.createQuery` with named parameters.
2. **VULN-002**: Reflected XSS in `CommentController.save` (High)
	* Remediation: Use HTML escaping to prevent XSS attacks.
	* Fix: Use `HtmlUtils.htmlEscape` to escape user-controlled input before rendering it in the HTML response.
3. **VULN-003**: Broken Access Control in `UserController.transfer` (High)
	* Remediation: Implement proper access control checks to prevent unauthorized access.
	* Fix: Add a check to ensure the user has the required role or permission before allowing the transfer operation.
4. **VULN-004**: Hardcoded Secrets in `SecretConfig.apiKey` (Medium)
	* Remediation: Store sensitive data securely using environment variables or a secrets manager.
	* Fix: Replace hardcoded secrets with environment variables or a secrets manager like HashiCorp Vault.
5. **VULN-005**: Security Misconfiguration in `SecurityConfig.insecureFilterChain` (Medium)
	* Remediation: Configure security settings properly to prevent security misconfiguration.
	* Fix: Update the security configuration to enable CSRF protection, secure headers, and proper authentication.
6. **VULN-006**: Reflected XSS in `UIController.greet` (Low)
	* Remediation: Use HTML escaping to prevent XSS attacks.
	* Fix: Use `HtmlUtils.htmlEscape` to escape user-controlled input before rendering it in the HTML response.

## Next Steps

1. **Implement parameterized queries**: Replace vulnerable code with parameterized queries to prevent SQL injection attacks.
2. **Enable CSRF protection**: Update the security configuration to enable CSRF protection and secure headers.
3. **Store sensitive data securely**: Replace hardcoded secrets with environment variables or a secrets manager like HashiCorp Vault.
