package com.diego.recipes.services;

import com.diego.recipes.models.Comment;
import com.diego.recipes.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    public Comment insert(Comment obj){
        return repository.save(obj);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

}

