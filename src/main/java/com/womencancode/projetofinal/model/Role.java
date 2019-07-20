package com.womencancode.projetofinal.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class Role {
    @Id
    private String id;

    @NonNull
    private String name;
}
