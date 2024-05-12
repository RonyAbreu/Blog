package com.ronyelison.blog.controller;

import com.ronyelison.blog.dto.comment.CommentRequest;
import com.ronyelison.blog.dto.comment.CommentResponse;
import com.ronyelison.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<CommentResponse> insertComment(@RequestBody @Valid CommentRequest commentRequest,
                                                         @PathVariable UUID userId,
                                                         @PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.insertComment(commentRequest, userId, postId));
    }

    @DeleteMapping("/{userId}/{commentId}")
    public ResponseEntity<Void> removeComment(@PathVariable UUID userID, @PathVariable Long commentId) throws NoPermissionException {
        commentService.removeComment(userID, commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/postId")
    public ResponseEntity<List<CommentResponse>> findCommentsByPost(@PathVariable Long postId){
        return ResponseEntity.ok(commentService.findCommentsByPost(postId));
    }
}
