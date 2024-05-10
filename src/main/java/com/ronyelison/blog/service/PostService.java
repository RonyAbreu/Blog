package com.ronyelison.blog.service;

import com.ronyelison.blog.dto.post.PostRequest;
import com.ronyelison.blog.dto.post.PostResponse;
import com.ronyelison.blog.entity.Post;
import com.ronyelison.blog.entity.User;
import com.ronyelison.blog.repository.PostRepository;
import com.ronyelison.blog.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.naming.NoPermissionException;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public PostResponse insertPost(PostRequest postRequest, UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Post post = new Post(postRequest.title(), postRequest.content(), user);
        postRepository.save(post);

        return post.entityToResponse();
    }

    public void removePost(Long postId, UUID userId) throws NoPermissionException {
        User loggedUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        if (!post.getCreator().equals(loggedUser)){
            throw new NoPermissionException("Usuário não tem permissão para remover esse post");
        }

        postRepository.delete(post);
    }

    public List<PostResponse> findAllPosts() {
        List<Post> posts = postRepository.findAll();

        if (posts.isEmpty()){
            throw new EntityNotFoundException("Nenhum post cadastrado");
        }

        return posts.stream().map(Post::entityToResponse).toList();
    }

    public PostResponse findPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado!"));

        return post.entityToResponse();
    }

    public PostResponse updatePost(PostRequest postRequest, Long postId, UUID userId) throws NoPermissionException{
        User loggedUser = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post não encontrado"));

        if (!post.getCreator().equals(loggedUser)){
            throw new NoPermissionException("Usuário não tem permissão para atualizar esse post");
        }

        updateData(post, postRequest);
        postRepository.save(post);

        return post.entityToResponse();
    }

    private void updateData(Post post, PostRequest postRequest) {
        post.setTitle(postRequest.title());
        post.setContent(postRequest.content());
    }
}
