package com.diego.recipes.services.implementation;

import com.diego.recipes.data.entity.Comment;
import com.diego.recipes.data.entity.Recipe;
import com.diego.recipes.repositories.RecipeRepository;
import com.diego.recipes.services.IRecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements IRecipeService {

    @Autowired
    private RecipeRepository repository;


    /**
     * Método que retorna todas as receitas salvas no banco
     * @return
     */
    public List<Recipe> findAllRecipes(){
        return repository.findAll();
    }

    /**
     * Método que faz a inserção da receita no banco
     * @param obj Receita a ser inserida
     * @return Receita inserida
     */
    public Recipe insertRecipe(Recipe obj){
        System.out.println(obj.toString());
        return repository.save(obj);
    }

    /**
     * Método que retorna a receita baseada no ID
     * @param id Id da receita pesquisada
     * @return
     */
    public Recipe findRecipeById(String id) {
        Optional<Recipe> recipeById = repository.findById(id);
        return recipeById.get();
    }

    /**
     * Método responsável por fazer um update nos dado da receita
     * @param obj Dados da nova receita
     * @param id Id da receita a ser alterada
     * @return
     */
    public Recipe updateRecipe(Recipe obj, String id){
        Recipe newObj = (repository.findById(id)).get();
        newObj = updateMiddleObjectData(newObj, obj);
        return repository.save(newObj);
    }

    /**
     * Método responsável por deletar uma receita
     * @param id Id da receita a ser deletada
     */
    public void deleteRecipe(String id){
        repository.deleteById(id);
    }

    /**
     * Método responsável por buscar receitas baseado no ingrediente
     * @param ingredient Nome do ingrediente pesquisado
     * @return Todas as receitas com o ingrediente
     */
    public List<Recipe> findRecipeByIngredient(String ingredient){
        return repository.findByIngredients(ingredient);
    }

    /**
     * Método responsável por buscar receitas baseado no titulo
     * @param search Nome da receita pesquisado
     * @return Todas as receitas com o titulo
     */
    public List<Recipe> findRecipeByTitle(String search){
        return repository.findByTitleContaining(search);
    }

    /**
     * Método responsável por buscar receitas baseado na descrição
     * @param search Parte da descrição
     * @return Todas as receitas com da descrição
     */
    public List<Recipe> findRecipeByDescription(String search){
        return repository.findByDescriptionContaining(search);
    }


    /**
     * Método responsável por inserir um "like" na receita
     * @param id Id da receita
     * @param userId Id do usuário que realizou o like
     * @return Receita atualizada com o like
     */
    public Recipe insertLikeInRecipe(String id, String userId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        updatedRecipe.addLike(userId);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    /**
     * Método responsável por remover um like da receita
     * @param id Id da receita
     * @param userId Id do usuário que realizou o deslike
     * @return Receita atualizada sem o ike
     */
    public Recipe deleteLikeFromRecipe(String id, String userId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        updatedRecipe.removeLike(userId);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    /**
     * Método responsável por inserir um comentário na receita
     * @param id Id da receita
     * @param message Conteúdo do comentário
     * @return Receita com o comentário inserido
     */
    public Recipe insertCommentInRecipe(String id, String message){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        Comment comment = new Comment(message);
        updatedRecipe.addComment(comment);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    /**
     * Método responsável por remover um comentário da receita
     * @param id Id da receita
     * @param commentId Id do comentário
     * @return Receita sem o comentário
     */
    public Recipe deleteCommentFromRecipe(String id, String commentId){
        Recipe actualRecipe = (repository.findById(id).get());
        Recipe updatedRecipe = actualRecipe;
        Comment comment = new Comment();
        comment.setId(commentId);
        updatedRecipe.removeComment(comment);
        updateRecipe(updatedRecipe, id);
        return updatedRecipe;
    }

    /**
     * Método de uso interno responsável por alterar os dados de uma Receita
     * @param newObj Receita com os dados atualizados
     * @param obj Receita a ser atualizada
     * @return Receita atualizada
     */
    public Recipe updateMiddleObjectData(Recipe newObj, Recipe obj){
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setIngredients(obj.getIngredients());
        newObj.setLikes(obj.getLikes());
        newObj.setComments(obj.getComments());
        return newObj;
    }

}
