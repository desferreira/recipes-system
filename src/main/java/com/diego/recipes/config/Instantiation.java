package com.diego.recipes.config;

import com.diego.recipes.models.Comment;
import com.diego.recipes.models.Recipe;
import com.diego.recipes.repositories.CommentRepository;
import com.diego.recipes.repositories.RecipeRepository;
import com.diego.recipes.services.CommentService;
import com.diego.recipes.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void run(String... args) throws Exception {

        commentRepository.deleteAll();
        recipeRepository.deleteAll();

        Recipe r1 = new Recipe("Feijao com arroz", "Um delicioso prato brasileiro", Arrays.asList("arroz", "feijao"));
        Recipe r2 = new Recipe("Bolo de cenoura", "Um autentico bolo de cenoura com cobertura de chocolate", Arrays.asList("farinha de trigo", "ovo", "leite", "chocolate", "cenoura"));

        Comment c1 = new Comment("Muito gostoso! ");
        Comment c2 = new Comment("Ótimo!");

        commentRepository.saveAll(Arrays.asList(c1, c2));

        r1.setComments(Arrays.asList(c1, c2));
        r2.setComments(Arrays.asList(c2));

        recipeRepository.saveAll(Arrays.asList(r1, r2));
    }
}
