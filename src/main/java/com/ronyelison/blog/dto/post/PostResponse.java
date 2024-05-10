package com.ronyelison.blog.dto.post;

import com.ronyelison.blog.dto.user.UserResponse;

public record PostResponse(
        Long id,
        String title,
        String content,
        UserResponse creator
) {
}
