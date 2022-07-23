package com.gabreuvcr.log.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInput {

    @Valid
    @NotNull
    private CustomerInput customer;

    @Valid
    @NotNull
    private RecipientInput recipient;

    @NotNull
    private BigDecimal fee;

    @Getter @Setter
    public static class RecipientInput {
        @NotBlank
        private String name;
        @NotBlank
        private String street;
        @NotBlank
        private String number;
        @NotBlank
        private String complement;
        @NotBlank
        private String neighborhood;
    }

    @Getter @Setter
    public static class CustomerInput {
        @NotNull
        private Long id;
    }

}
