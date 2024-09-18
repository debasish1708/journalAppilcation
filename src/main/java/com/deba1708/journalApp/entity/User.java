package com.deba1708.journalApp.entity;

import  jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "users")
@Data
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NotBlank(message = "user can not be blank or empty")
    @NotNull
    private String userName;

    private String email;

    private boolean sentimentAnalysis;

    @NotBlank(message = "user can not be blank or empty")
    @NotNull
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries=new ArrayList<>();

    private List<String> roles;
}

// Document is for Interact with Database which field and collections are to be created