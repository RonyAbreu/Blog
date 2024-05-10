package com.ronyelison.blog.service;

import com.ronyelison.blog.dto.comment.CommentRequest;
import com.ronyelison.blog.dto.comment.CommentResponse;
import com.ronyelison.blog.entity.Comment;
import com.ronyelison.blog.entity.Post;
import com.ronyelison.blog.entity.User;
import com.ronyelison.blog.repository.CommentRepository;
import com.ronyelison.blog.repository.PostRepository;
import com.ronyelison.blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public CommentResponse insertComment(CommentRequest commentRequest, UUID userId, Long postId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        Comment comment = new Comment(commentRequest.text(), user, post);
        commentRepository.save(comment);

        return comment.entityToResponse();
    }

    public void removeComment(UUID userId, Long commentId) throws NoPermissionException {
        User loggedUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrdo!"));

        if (!comment.getCreator().equals(loggedUser)){
            throw new NoPermissionException("Usuário não tem permissão para remover esse comentário");
        }

        commentRepository.delete(comment);
    }

    public List<CommentResponse> findCommentsByPost(Long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        List<Comment> comments = commentRepository.findByPostId(postId);

        if (comments.isEmpty()){
            throw new EntityNotFoundException("Esse post não possui comentários");
        }

        return comments.stream().map(Comment::entityToResponse).toList();
    }
}
