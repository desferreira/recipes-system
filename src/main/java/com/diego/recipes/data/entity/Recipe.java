package com.diego.recipes.data.entity;


import com.diego.recipes.data.form.RecipeForm;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@EqualsAndHashCode @ToString
public class Recipe implements Serializable {

    @Id
    @Getter @Setter
    private String id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private List<String> ingredients;

    @Getter @Setter
    private List<String> likes;

    @Getter @Setter
    private List<Comment> comments;

    public Recipe(String title, String description, List<String> ingredients) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    /**
     * Método responsável por inserir um comentário na lista
     * @param comment
     */
    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    /**
     * Método responsável por remover um comentário da lista
     * @param comment
     */
    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    /**
     * Método responsável por inserir um like na lista
     * @param like
     */
    public void addLike(String like){
        this.likes.add(like);
    }

    /**
     * Método responsável por remover um like da lista
     * @param like
     */
    public void removeLike(String like){
        this.likes.remove(like);
    }

    public Recipe createFromRecipeForm(RecipeForm form){
        this.description = form.getDescription();
        this.title = form.getTitle();
        this.ingredients = form.getIngredients();
        return new Recipe(this.title, this.description, this.ingredients);
    }

}
