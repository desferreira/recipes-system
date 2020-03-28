package com.diego.recipes.services;

import com.diego.recipes.data.entity.Recipe;

import java.util.List;

public interface IRecipeService {

    List<Recipe> findAllRecipes();
    Recipe insertRecipe(Recipe obj);
    Recipe findRecipeById(String id);
    Recipe updateRecipe(Recipe obj, String id);
    void deleteRecipe(String id);
    List<Recipe> findRecipeByIngredient(String ingredient);
    List<Recipe> findRecipeByTitle(String search);
    List<Recipe> findRecipeByDescription(String search);
    Recipe insertLikeInRecipe(String id, String userId);
    Recipe deleteLikeFromRecipe(String id, String userId);
    Recipe insertCommentInRecipe(String id, String message);
    Recipe deleteCommentFromRecipe(String id, String commentId);
    Recipe updateMiddleObjectData(Recipe newObj, Recipe obj);
}
