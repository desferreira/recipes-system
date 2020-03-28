package com.diego.recipes.data.entity;


import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString @EqualsAndHashCode
public class Comment implements Serializable {

    @Getter @Setter
    private String id;

    @Getter @Setter
    private String comment;

    public Comment(String comment) {
        this.id = generateId();
        this.comment = comment;
    }

    private String generateId(){
        return UUID.randomUUID().toString();
    }

}
