package com.ronyelison.blog.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank
        @Size(min = 5, max = 100)
        String title,
        @NotBlank
        @Size(min = 5, max = 255)
        String content
) {
}
