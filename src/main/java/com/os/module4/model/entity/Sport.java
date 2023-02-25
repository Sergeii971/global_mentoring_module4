package com.os.module4.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("sport")
public class Sport implements BaseEntity {
    private String id;
    @Indexed
    private String sportName;
    private SportProficiency sportProficiency;

}
