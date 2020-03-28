package com.diego.recipes.services;

import com.diego.recipes.models.Comment;
import com.diego.recipes.models.Recipe;
import com.diego.recipes.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository repository;

    public List<Recipe> findAll(){
        return repository.findAll();
    }

    public Recipe insert(Recipe obj){
        return repository.save(obj);
    }

    public Recipe findById(String id) {
        Optional<Recipe> recipeById = repository.findById(id);
        return recipeById.get();
    }

    public Recipe update(Recipe obj, String id){
        Recipe newObj = (repository.findById(id)).get();
        newObj = updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public List<Recipe> findByIngredient(String ingredient){
        return repository.findByIngredients(ingredient);
    }

    public List<Recipe> findByTitle(String search){
        return repository.findByTitleContaining(search);
    }

    public List<Recipe> findByDescription(String search){
        return repository.findByDescriptionContaining(search);
    }

    public Recipe insertLike(String id, String userId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        updatedRecipe.addLike(userId);
        update(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe deleteLike(String id, String userId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        updatedRecipe.removeLike(userId);
        update(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe insertComment(String id, String message){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        Comment comment = new Comment(message);
        updatedRecipe.addComment(comment);
        update(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe deleteComment(String id, String commentId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        Comment comment = new Comment();
        comment.setId(commentId);
        updatedRecipe.removeComment(comment);
        update(updatedRecipe, id);
        return updatedRecipe;
    }

    private Recipe updateData(Recipe newObj, Recipe obj){
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setIngredients(obj.getIngredients());
        newObj.setLikes(obj.getLikes());
        newObj.setComments(obj.getComments());
        return newObj;
    }

}
