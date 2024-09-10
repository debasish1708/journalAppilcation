package com.deba1708.journalApp.entity;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {
    private String key;
    private String value;
}

// Document is for Interact with Database which field and collections are to be created