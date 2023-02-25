package com.os.module4.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserDto implements BaseDto {
    private String id;
    private String email;
    private String fullName;
    private Date birthDate;
    private String gender;

    private SportDto sport;
}
