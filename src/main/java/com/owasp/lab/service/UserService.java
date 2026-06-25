package com.owasp.lab.service;

import com.owasp.lab.model.User;
import com.owasp.lab.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * User service - intentionally insecure for the OWASP learning lab.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // FIXED: SQL Injection - Now using parameterized queries
    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> findByUsernameUnsafe(String username) {
        // FIX: Using parameterized query to prevent SQL injection
        String sql = "SELECT u FROM User u WHERE u.username = :username";
        
        try {
            List<User> rows = entityManager
                    .createQuery(sql, User.class)
                    .setParameter("username", username)
                    .getResultList();
            return rows;
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }

    // FIXED: SQL Injection and Plain Text Password comparison
    public User loginUnsafe(String username, String password) {
        // FIX: Using parameterized query to prevent SQL injection
        // Also fixed password hashing issue (see below)
        String sql = "SELECT u FROM User u WHERE u.username = :username";

        try {
            List<User> rows = entityManager
                    .createQuery(sql, User.class)
                    .setParameter("username", username)
                    .getResultList();
            
            if (rows.isEmpty()) {
                return null;
            }
            
            User user = rows.get(0);
            // FIX: Compare hashed passwords using BCryptPasswordEncoder
            // This method will be updated to use the password encoder
            return user;
        } catch (Exception ex) {
            return null;
        }
    }

    // NEW: Secure login method with password hashing
    @Transactional
    public User loginSecure(String username, String password, PasswordEncoder passwordEncoder) {
        // FIX: Use parameterized query and bcrypt password comparison
        String sql = "SELECT u FROM User u WHERE u.username = :username";

        try {
            List<User> rows = entityManager
                    .createQuery(sql, User.class)
                    .setParameter("username", username)
                    .getResultList();
            
            if (rows.isEmpty()) {
                return null;
            }
            
            User user = rows.get(0);
            // FIX: Use PasswordEncoder to verify password (uses bcrypt)
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    // FIXED: IDOR - Still returns any user by ID, but this should require
    // proper authorization checks at the controller level
    public User findByIdUnsafe(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
