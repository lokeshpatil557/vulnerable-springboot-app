package com.owasp.lab.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Base64;

/**
 * Insecure deserialisation demo - FIXED.
 *
 * VULNERABILITY FIXED (OWASP A08:2021 - Software and Data Integrity Failures):
 *
 * This endpoint now rejects deserialization to prevent gadget chain attacks.
 * Java native deserialisation is dangerous and should be avoided.
 */
@RestController
@RequestMapping("/api/deserialize")
public class InsecureDeserializationController {

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> deserialize(@RequestBody String body) throws Exception {
        // FIX: Reject deserialization entirely - this is a dangerous operation
        // In production, never deserialize untrusted data
        return ResponseEntity.badRequest().body(
            "Deserialization endpoint is disabled for security reasons. " +
            "Java native deserialization is vulnerable to remote code execution." );
    }
}
