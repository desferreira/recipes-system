package com.diego.recipes.services;

import com.diego.recipes.models.Recipe;
import com.diego.recipes.repositories.RecipeRepository;
import com.diego.recipes.repositories.exception.ResourceNotFound;
import com.diego.recipes.repositories.exception.ResourceNotFoundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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
        try {
            Optional<Recipe> recipeById = repository.findById(id);
            return recipeById.get();
        } catch (NoSuchElementException e) {
            throw new ResourceNotFound(id);

        }
    }

    public Recipe update(Recipe obj, String id){
        Optional<Recipe> newObj = repository.findById(id);
        updateData(newObj.get(), obj);
        return repository.save(newObj.get());
    }

    public void delete(String id){
        repository.deleteById(id);
    }

    public List<Recipe> findByIngredient(String ingredient){
        return repository.findByIngredients(ingredient);
    }

    public List<Recipe> findByTitleOrDescription(String search){
        return repository.findByTitleContaining(search);
    }

    public void updateData(Recipe newObj, Recipe obj){
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setIngredients(obj.getIngredients());
    }



}
