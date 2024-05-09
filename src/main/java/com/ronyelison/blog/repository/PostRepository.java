package com.ronyelison.blog.repository;

import com.ronyelison.blog.entity.Post;
import com.ronyelison.blog.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {
}
