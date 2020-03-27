package com.diego.recipes.models;


import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;


public class Comment implements Serializable {

    private String id;
    private String comment;

    public Comment() {
    }

    public Comment(String comment) {

        this.id = generateId();
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String generateId(){
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
