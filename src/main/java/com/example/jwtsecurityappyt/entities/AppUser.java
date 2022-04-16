package com.example.jwtsecurityappyt.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "users")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class AppUser {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    //pour pas que le mdf s'affiche meme en b64
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private boolean activated;
    @DBRef
    private Collection<AppRole> roles = new ArrayList<>();

}
