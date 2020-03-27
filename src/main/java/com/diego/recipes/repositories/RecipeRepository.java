package com.diego.recipes.repositories;

import com.diego.recipes.models.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    List<Recipe> findByIngredients(String ingredient);
    List<Recipe> findByTitleContaining(String search);
    List<Recipe> findByDescriptionContaining(String search);

}
