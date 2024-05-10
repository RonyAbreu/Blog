package com.ronyelison.blog.dto.comment;

import com.ronyelison.blog.dto.post.PostResponse;
import com.ronyelison.blog.dto.user.UserResponse;

public record CommentResponse(
        Long id,
        String text,
        PostResponse post,
        UserResponse creator
) {
}
