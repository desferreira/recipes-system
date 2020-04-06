package com.diego.recipes.data.form;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class RecipeForm implements Serializable {

    @Getter
    private String title;

    @Getter
    private String description;

    @Getter
    private List<String> ingredients;
}
