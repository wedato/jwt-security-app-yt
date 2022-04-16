package com.example.jwtsecurityappyt.entities;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data @ToString
public class AppRole {

    public AppRole(String roleName) {
        this.roleName = roleName;
    }

    @Id
    private String id;
    @Indexed(unique = true)
    private String roleName;
}
