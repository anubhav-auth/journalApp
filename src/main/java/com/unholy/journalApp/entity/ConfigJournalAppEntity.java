package com.unholy.journalApp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configJournalApp")
@Data
@NoArgsConstructor
public class ConfigJournalAppEntity {

    private String key;
    private String value;


}
