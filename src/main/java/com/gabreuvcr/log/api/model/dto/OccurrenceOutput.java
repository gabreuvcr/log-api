package com.gabreuvcr.log.api.model.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OccurrenceOutput {
    
    private Long id;
    private String description;
    private OffsetDateTime registrationDate;

}
