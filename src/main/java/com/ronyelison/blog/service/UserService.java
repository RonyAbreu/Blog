package com.ronyelison.blog.service;

import com.ronyelison.blog.dto.user.UserRequest;
import com.ronyelison.blog.dto.user.UserResponse;
import com.ronyelison.blog.entity.User;
import com.ronyelison.blog.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse insertUser(UserRequest userRequest){
        Optional<User> user = userRepository.findByEmail(userRequest.email());

        if (user.isPresent()){
            throw new EntityExistsException("Usuário já existe! Tente novamente.");
        }

        User validUser = new User(userRequest);
        userRepository.save(validUser);

        return validUser.entityToResponse();
    }

    public void removeUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        userRepository.delete(user);
    }

    public UserResponse findUserById(UUID id){
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"))
                .entityToResponse();
    }
}
