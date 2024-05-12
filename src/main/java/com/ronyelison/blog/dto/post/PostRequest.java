package com.ronyelison.blog.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 5, max = 100, message = "Caracteres inválidos 5-100")
        String title,
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 5, max = 255, message = "Caracteres inválidos 5-255")
        String content
) {
}
