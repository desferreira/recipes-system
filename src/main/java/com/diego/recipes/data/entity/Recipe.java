package com.diego.recipes.data.entity;


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

}
