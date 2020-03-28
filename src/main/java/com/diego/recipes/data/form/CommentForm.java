package com.diego.recipes.data.form;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentForm {

    @Getter
    private String message;

}
