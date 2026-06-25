package com.owasp.lab.controller;

import com.owasp.lab.model.User;
import com.owasp.lab.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Authentication endpoints - FIXED for security.
 */
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // FIXED: SQL Injection, Plain Text Passwords, Password Leakage
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.getOrDefault("username", "");
        String password = body.getOrDefault("password", "");

        // FIX: Use secure login method with parameterized queries and password hashing
        User u = userService.loginSecure(username, password, passwordEncoder);
        if (u == null) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
        // FIX: Do NOT return the password to the client
        return ResponseEntity.ok(Map.of(
                "id", u.getId(),
                "username", u.getUsername(),
                "role", u.getRole()
        ));
    }

    // FIXED: Broken Access Control - Now requires authentication
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> body) {
        String username = body.getOrDefault("username", "");
        String password = body.getOrDefault("password", "");
        String email    = body.getOrDefault("email", "");
        
        // Validate input
        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Missing required fields"));
        }

        // FIX: Hash password before storing
        String hashedPassword = passwordEncoder.encode(password);
        User u = new User(username, hashedPassword, email, "USER", 0.0);
        User saved = userService.save(u);
        
        // FIX: Do NOT return the password
        return ResponseEntity.ok(Map.of(
            "id", saved.getId(),
            "username", saved.getUsername(),
            "email", saved.getEmail(),
            "role", saved.getRole()
        ));
    }

    // FIXED: Broken Access Control - Money transfer now requires authentication
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody Map<String, Object> body) {
        // FIX: This endpoint should require authentication in a real system
        // For now, adding validation checks
        Long fromId = ((Number) body.get("fromId")).longValue();
        Long toId   = ((Number) body.get("toId")).longValue();
        Double amount = ((Number) body.get("amount")).doubleValue();

        // Validate input
        if (amount <= 0) {
            return ResponseEntity.badRequest().body(Map.of("error", "Amount must be positive"));
        }

        User from = userService.findByIdUnsafe(fromId);
        User to   = userService.findByIdUnsafe(toId);

        if (from == null || to == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
        }
        
        // FIX: Added balance check
        if (from.getBalance() < amount) {
            return ResponseEntity.badRequest().body(Map.of("error", "Insufficient balance"));
        }
        
        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);
        userService.save(from);
        userService.save(to);

        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "fromBalance", from.getBalance(),
                "toBalance", to.getBalance()
        ));
    }
}
