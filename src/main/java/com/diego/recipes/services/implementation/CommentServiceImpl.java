package com.diego.recipes.services.implementation;

import com.diego.recipes.data.entity.Comment;
import com.diego.recipes.repositories.CommentRepository;
import com.diego.recipes.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentRepository repository;

    public Comment insertComment(Comment obj){
        return repository.save(obj);
    }

    public void deleteComment(String id){
        repository.deleteById(id);
    }

    public Comment findCommentById(String id){
        return (repository.findById(id)).get();
    }


}

