# BUILD & DEPLOYMENT INSTRUCTIONS

## Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher
- Git (for version control)

## Building the Project

### Option 1: Using Maven (Recommended)

```bash
cd vulnerable-springboot-app
mvn clean package
```

This will:
1. Clean previous builds
2. Compile all Java source files
3. Run all unit tests
4. Package the application as JAR

**Output**: `target/vulnerable-spring-app-1.0.0.jar`

### Option 2: Using Docker

```bash
docker build -t vulnerable-spring-app:1.0.0 .
docker run -p 8080:8080 vulnerable-spring-app:1.0.0
```

### Option 3: Using IDE (VS Code, IntelliJ IDEA)

1. Import the project into your IDE
2. IDE will detect Maven configuration automatically
3. Right-click on `pom.xml` → "Run as" → "Maven Build"
4. Enter goal: `clean package`

## Running the Application

### From JAR

```bash
java -jar target/vulnerable-spring-app-1.0.0.jar
```

Server will start on: **http://localhost:8080**

### From IDE

Right-click on `VulnerableSpringAppApplication.java` → "Run as Java Application"

### From Maven

```bash
mvn spring-boot:run
```

## Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=AuthControllerTest
```

### Generate Test Coverage Report

```bash
mvn clean test jacoco:report
# Report location: target/site/jacoco/index.html
```

## Security Verification

### Verify Fixes Applied

1. **SQL Injection Fix**
   ```bash
   # Test parameterized queries with special characters
   curl -X POST http://localhost:8080/api/login \
     -H "Content-Type: application/json" \
     -d '{"username":"'"'"' OR '"'"'1'"'"'='"'"'1","password":"test"}'
   ```
   Expected: Authentication fails (not vulnerable)

2. **XSS Fix**
   ```bash
   # Test HTML escaping
   curl "http://localhost:8080/api/comment/greet?name=<script>alert('XSS')</script>"
   ```
   Expected: Script tags are escaped as text (not executed)

3. **Password Hashing**
   - Inspect database: passwords should be bcrypt hashes, not plaintext
   - Login response should not contain password field

4. **Authentication**
   ```bash
   # Try accessing protected endpoint without auth
   curl -X POST http://localhost:8080/api/products \
     -H "Content-Type: application/json" \
     -d '{"name":"Test","description":"Test"}'
   ```
   Expected: 401 Unauthorized or 403 Forbidden

## Troubleshooting

### "Maven command not found"
- Install Maven from: https://maven.apache.org/install.html
- Add Maven bin directory to PATH

### "Java version mismatch"
```bash
java -version  # Should be 17 or higher
mvn -version   # Should show Java 17+
```

### "Tests failing"
```bash
# Run with verbose output
mvn clean test -X

# Check test output
cat target/surefire-reports/*.txt
```

### "Port 8080 already in use"
```bash
# Change port in application.properties
server.port=8081
```

## Performance Notes

- Initial build: ~1-2 minutes (downloading dependencies)
- Incremental build: ~10-30 seconds
- Test execution: ~30 seconds
- Full `mvn clean package`: ~2-3 minutes

## Next Steps

1. Deploy to staging environment
2. Run security testing (SAST, DAST, penetration testing)
3. Configure HTTPS/TLS
4. Set up secrets management (Vault, AWS Secrets Manager, etc.)
5. Implement monitoring and logging
6. Deploy to production with proper secrets configuration

## Additional Resources

- Spring Boot Documentation: https://spring.io/projects/spring-boot
- OWASP Top 10: https://owasp.org/www-project-top-ten/
- Maven Documentation: https://maven.apache.org/guides/
- Spring Security: https://spring.io/projects/spring-security

## Support

For issues or questions:
1. Check application logs: `target/logs/`
2. Run Maven with debug: `mvn -X clean package`
3. Consult Java Spring Boot documentation
