package com.owasp.lab.controller;

import com.owasp.lab.model.Comment;
import com.owasp.lab.service.CommentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.text.StringEscapeUtils;

import java.util.List;

/**
 * Renders comments as HTML with proper escaping to prevent XSS.
 */
@RestController
@RequestMapping("/comments")
public class CommentViewController {

    private final CommentService commentService;

    public CommentViewController(CommentService commentService) {
        this.commentService = commentService;
    }

    // FIXED: XSS - Stored - Now escaping HTML entities
    @GetMapping(produces = MediaType.TEXT_HTML_VALUE)
    public String viewAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body><h1>Comments</h1>");
        List<Comment> comments = commentService.findAll();
        for (Comment c : comments) {
            // FIX: Escape HTML entities to prevent XSS
            String escapedAuthor = StringEscapeUtils.escapeHtml4(c.getAuthor());
            String escapedBody = StringEscapeUtils.escapeHtml4(c.getBody());
            sb.append("<div class='comment'>")
              .append("<b>").append(escapedAuthor).append(":</b> ")
              .append(escapedBody)
              .append("</div>");
        }
        sb.append("</body></html>");
        return sb.toString();
    }

    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String viewOne(@PathVariable Long id) {
        Comment c = commentService.findById(id);
        if (c == null) {
            return "<html><body>Not found</body></html>";
        }
        // FIX: Escape HTML entities to prevent XSS
        String escapedAuthor = StringEscapeUtils.escapeHtml4(c.getAuthor());
        String escapedBody = StringEscapeUtils.escapeHtml4(c.getBody());
        return "<html><body><h1>Comment</h1><div><b>"
                + escapedAuthor + ":</b> " + escapedBody + "</div></body></html>";
    }
}
