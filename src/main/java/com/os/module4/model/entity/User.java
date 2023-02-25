package com.os.module4.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document("user")
public class User implements BaseEntity {

    @Id
    private String id;
    @Indexed(name = "email_index")
    @Field("email")
    private String email;

    @Field("fullName")
    private String fullName;
    private Date birthDate;
    @Field("gender")
    private String gender;
    private Sport sport;

}
