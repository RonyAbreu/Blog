package com.ronyelison.blog.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentRequest(
        @NotBlank(message = "Campo obrigatório")
        @Size(min = 1, max = 155, message = "Caracteres inválidos 1-155")
        String text
) {

}
