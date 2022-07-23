package com.gabreuvcr.log.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class Error {
    @Getter
    @AllArgsConstructor
    public static class Field {
        private String name;
        private String message;
    }

    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private List<Field> fields;
}
