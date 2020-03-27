package com.diego.recipes.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.*;

@Document
public class Recipe implements Serializable {

    @Id
    private String id;
    private String title;
    private String description;
    private List<String> ingredients = new ArrayList<>();
    private List<String> likes = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String title, String description, List<String> ingredients, List<String> likes) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public void addLike(String like){
        this.likes.add(like);
    }

    public void removeLike(String like){
        this.likes.remove(like);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) &&
                Objects.equals(title, recipe.title) &&
                Objects.equals(description, recipe.description) &&
                Objects.equals(ingredients, recipe.ingredients) &&
                Objects.equals(likes, recipe.likes) &&
                Objects.equals(comments, recipe.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, ingredients, likes, comments);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
