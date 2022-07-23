package com.gabreuvcr.log.api.model.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OccurrenceInput {
    
    @NotBlank
    private String description;

}
