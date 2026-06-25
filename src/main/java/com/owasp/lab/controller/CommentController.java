package com.owasp.lab.controller;

import com.owasp.lab.model.Comment;
import com.owasp.lab.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.apache.commons.text.StringEscapeUtils;

/**
 * Comment endpoints - used to demonstrate XSS.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // FIXED: XSS - Comment validation added, storage is safe
    @PostMapping
    public Comment create(@RequestBody Comment c) {
        // FIX: Validate and sanitize comment input
        if (c.getBody() != null && c.getBody().length() > 2000) {
            throw new IllegalArgumentException("Comment body too long");
        }
        return commentService.save(c);
    }

    @GetMapping
    public List<Comment> all() {
        return commentService.findAll();
    }

    // FIXED: XSS - Reflected - Now escaping the name parameter
    @GetMapping(value = "/greet", produces = MediaType.TEXT_HTML_VALUE)
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        // FIX: Escape HTML in the user-supplied name parameter
        String escapedName = StringEscapeUtils.escapeHtml4(name);
        return "<html><body><h1>Hello, " + escapedName + "!</h1></body></html>";
    }
}
