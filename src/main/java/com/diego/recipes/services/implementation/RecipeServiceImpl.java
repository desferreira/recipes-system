package com.diego.recipes.services.implementation;

import com.diego.recipes.data.entity.Comment;
import com.diego.recipes.data.entity.Recipe;
import com.diego.recipes.repositories.RecipeRepository;
import com.diego.recipes.services.IRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements IRecipeService {

    @Autowired
    private RecipeRepository repository;

    public List<Recipe> findAllRecipes(){
        return repository.findAll();
    }

    public Recipe insertRecipe(Recipe obj){
        System.out.println(obj.toString());
        return repository.save(obj);
    }

    public Recipe findRecipeById(String id) {
        Optional<Recipe> recipeById = repository.findById(id);
        return recipeById.get();
    }

    public Recipe updateRecipe(Recipe obj, String id){
        Recipe newObj = (repository.findById(id)).get();
        newObj = updateMiddleObjectData(newObj, obj);
        return repository.save(newObj);
    }

    public void deleteRecipe(String id){
        repository.deleteById(id);
    }

    public List<Recipe> findRecipeByIngredient(String ingredient){
        return repository.findByIngredients(ingredient);
    }

    public List<Recipe> findRecipeByTitle(String search){
        return repository.findByTitleContaining(search);
    }

    public List<Recipe> findRecipeByDescription(String search){
        return repository.findByDescriptionContaining(search);
    }

    public Recipe insertLikeInRecipe(String id, String userId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        updatedRecipe.addLike(userId);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe deleteLikeFromRecipe(String id, String userId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        updatedRecipe.removeLike(userId);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe insertCommentInRecipe(String id, String message){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        Comment comment = new Comment(message);
        updatedRecipe.addComment(comment);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe deleteCommentFromRecipe(String id, String commentId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        Comment comment = new Comment();
        comment.setId(commentId);
        updatedRecipe.removeComment(comment);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    public Recipe updateMiddleObjectData(Recipe newObj, Recipe obj){
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setIngredients(obj.getIngredients());
        newObj.setLikes(obj.getLikes());
        newObj.setComments(obj.getComments());
        return newObj;
    }

}
