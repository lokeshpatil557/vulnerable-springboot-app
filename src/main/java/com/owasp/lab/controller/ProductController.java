package com.owasp.lab.controller;

import com.owasp.lab.model.Product;
import com.owasp.lab.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Simple product endpoints - FIXED for security.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> list() {
        return productService.findAll();
    }

    // FIXED: Broken Access Control - Now requires authentication
    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Product> create(@RequestBody Product p) {
        // FIX: Added authentication requirement via @PreAuthorize
        // Only authenticated users with USER or ADMIN role can create products
        return ResponseEntity.ok(productService.save(p));
    }
}
