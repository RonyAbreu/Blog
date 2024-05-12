package com.ronyelison.blog.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 3, max = 30, message = "Caracteres inválidos 3-30")
        String name,
        @NotBlank(message = "Campo obrigatório")
        @Email(message = "Email inválido")
        @Size(min = 3, max = 255, message = "Caracteres inválidos 3-255")
        String email,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 8, max = 20, message = "Caracteres inválidos 8-20")
        String password
) {
}
