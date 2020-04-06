package com.diego.recipes.controllers;

import com.diego.recipes.data.entity.Recipe;
import com.diego.recipes.data.form.CommentForm;
import com.diego.recipes.data.form.RecipeForm;
import com.diego.recipes.services.implementation.RecipeServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/recipe")
public class RecipeController {

    @Autowired
    private RecipeServiceImpl service;

    @GetMapping
    public ResponseEntity<List<Recipe>> findAll(){
        List<Recipe> allRecipes = service.findAllRecipes();
        return ResponseEntity.ok().body(allRecipes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> findRecipeById(@PathVariable String id){
        Recipe recipe = service.findRecipeById(id);
        return ResponseEntity.ok().body(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipe> insert(@RequestBody RecipeForm obj){
        Recipe recipe = new Recipe();
        recipe = recipe.createFromRecipeForm(obj);
        service.insertRecipe(recipe);
        return ResponseEntity.ok().body(recipe);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Recipe> update(@PathVariable String id, @RequestBody Recipe obj){
        Recipe recipe = service.findRecipeById(id);
        recipe = service.updateRecipe(obj, id);
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        service.deleteRecipe(id);
        return ResponseEntity.ok().body("A receita foi deletada com sucesso!");
    }

    @GetMapping(value = "/ingredient")
    public ResponseEntity<List<Recipe>> findByIngredient(@RequestParam String ingredient){
        List<Recipe> recipesContainingIngredient = service.findRecipeByIngredient(ingredient);
        return ResponseEntity.ok().body(recipesContainingIngredient);
    }

    @GetMapping(value = "/search/title")
    public ResponseEntity<List<Recipe>> findByTitle(@RequestParam String search){
        List<Recipe> recipesContainingSearch = service.findRecipeByTitle(search);
        return ResponseEntity.ok().body(recipesContainingSearch);
    }

    @GetMapping(value = "/search/description")
    public ResponseEntity<List<Recipe>> findByDescription(@RequestParam String search){
        List<Recipe> recipesContainingSearch = service.findRecipeByDescription(search);
        return ResponseEntity.ok().body(recipesContainingSearch);
    }

    @PostMapping(value = "/{id}/like/{userId}")
    public ResponseEntity<Recipe> likeRecipe(@PathVariable String id, @PathVariable String userId){
        Recipe recipe = service.insertLikeInRecipe(id, userId);
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping(value = "/{id}/like/{userId}")
    public ResponseEntity<Recipe> unlikeRecipe(@PathVariable String id, @PathVariable String userId){
        Recipe recipe = service.deleteLikeFromRecipe(id, userId);
        return ResponseEntity.ok().body(recipe);
    }


    @PostMapping(value = "/{id}/comment")
    public ResponseEntity<Recipe> commentRecipe(@PathVariable String id, @RequestBody CommentForm messageForm){
        Recipe recipe = service.insertCommentInRecipe(id, messageForm.getMessage());
        return ResponseEntity.ok().body(recipe);
    }


    @DeleteMapping(value = "/{id}/comment/{commentId}")
    public ResponseEntity<Recipe> removeComment(@PathVariable String id, @PathVariable String commentId){
        Recipe recipe = service.deleteCommentFromRecipe(id, commentId);
        return ResponseEntity.ok().body(recipe);
    }

}
