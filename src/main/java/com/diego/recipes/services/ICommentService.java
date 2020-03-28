package com.diego.recipes.services;

import com.diego.recipes.data.entity.Comment;

public interface ICommentService {

    Comment insertComment(Comment obj);
    void deleteComment(String id);
    Comment findCommentById(String id);


}
