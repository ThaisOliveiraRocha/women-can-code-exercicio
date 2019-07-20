package com.womencancode.projetofinal.model;

import lombok.Builder;
import lombok.Data;
import com.mongodb.lang.NonNull;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@Builder
@Wither
public class User {
    @Id
    private String id;
    private String name;
    private String lastName;

    @NonNull
    private String username;

    @NonNull
    private String email;

    //datanascimento

    @DBRef
    private Role role;
}
