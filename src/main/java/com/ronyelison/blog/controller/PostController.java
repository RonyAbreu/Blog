package com.ronyelison.blog.controller;

import com.ronyelison.blog.dto.post.PostRequest;
import com.ronyelison.blog.dto.post.PostResponse;
import com.ronyelison.blog.service.PostService;
import jakarta.validation.Valid;
import org.apache.juli.logging.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ap/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<PostResponse> insertPost(@RequestBody @Valid PostRequest postRequest,
                                                   @PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.insertPost(postRequest, id));
    }

    @DeleteMapping("/{postId}/{userId}")
    public ResponseEntity<Void> removePost(@PathVariable Long postId, @PathVariable UUID userId) throws NoPermissionException {
        postService.removePost(postId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> findAllPosts(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> findPostById(@PathVariable Long id){
        return ResponseEntity.ok(postService.findPostById(id));
    }

    @PutMapping("/{postId}/{userId}")
    public ResponseEntity<PostResponse> updatePost(@RequestBody @Valid PostRequest postRequest,
                                                   @PathVariable Long postId,
                                                   @PathVariable UUID userId) throws NoPermissionException {
        return ResponseEntity.ok(postService.updatePost(postRequest, postId, userId));
    }
}
