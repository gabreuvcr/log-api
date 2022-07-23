package com.gabreuvcr.log.api.model.dto;

import com.gabreuvcr.log.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryOutput {
    
    private Long id;
    private CustomerOutput customer;
    private RecipientOutput recipient;
    private BigDecimal fee;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime finalizationDate;

    @Getter @Setter
    public static class CustomerOutput {
        private Long id;
        private String name;
    }

    @Getter @Setter
    public static class RecipientOutput {
        private String name;
        private String street;
        private String number;
        private String complement;
        private String neighborhood;

    }

}
