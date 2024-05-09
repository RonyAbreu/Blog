package com.ronyelison.blog.repository;

import com.ronyelison.blog.entity.Comment;
import com.ronyelison.blog.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}
