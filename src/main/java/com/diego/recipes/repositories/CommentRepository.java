package com.diego.recipes.repositories;

import com.diego.recipes.data.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {


}
