package com.diego.recipes.controllers;

import com.diego.recipes.models.Recipe;
import com.diego.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

    @Autowired
    private RecipeService service;

    @GetMapping
    public ResponseEntity<List<Recipe>> findAll(){
        List<Recipe> allRecipes = service.findAll();
        return ResponseEntity.ok().body(allRecipes);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable String id){
        Recipe recipe = service.findById(id);
        return ResponseEntity.ok().body(recipe);
    }

    @PostMapping
    public ResponseEntity<Recipe> insert(@RequestBody Recipe obj){
        service.insert(obj);
        return ResponseEntity.ok().body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Recipe> update(@PathVariable String id, @RequestBody Recipe obj){
        Recipe recipe = service.findById(id);
        recipe = service.update(obj, id);
        return ResponseEntity.ok().body(recipe);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.ok().body("A receita foi deletada com sucesso!");
    }

    @GetMapping(value = "/ingredient")
    public ResponseEntity<List<Recipe>> findByIngredient(@RequestParam String ingredient){
        List<Recipe> recipesContainingIngredient = service.findByIngredient(ingredient);
        return ResponseEntity.ok().body(recipesContainingIngredient);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Recipe>> findByTitleOrDiscription(@RequestParam String search){
        List<Recipe> recipesContainigSearch = service.findByTitleOrDescription(search);
        return ResponseEntity.ok().body(recipesContainigSearch);
    }


}
