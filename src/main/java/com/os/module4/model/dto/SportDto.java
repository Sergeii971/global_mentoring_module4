package com.os.module4.model.dto;

import com.os.module4.model.entity.SportProficiency;
import lombok.Data;

@Data
public class SportDto implements BaseDto {
    private String id;
    private String sportName;
    private SportProficiencyDto sportProficiency;
}
