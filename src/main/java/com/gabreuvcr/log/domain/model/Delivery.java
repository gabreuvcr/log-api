package com.gabreuvcr.log.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.gabreuvcr.log.domain.exception.DomainException;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @Embedded
    private Recipient recipient;

    private BigDecimal fee;
    
    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrences = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private OffsetDateTime orderDate;
    private OffsetDateTime finalizationDate;

    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setRegistrationDate(OffsetDateTime.now());
        occurrence.setDelivery(this);
        occurrences.add(occurrence);
        
        return occurrence;
    }

    public boolean canChangeStatus() {
        return DeliveryStatus.PENDING.equals(status);
    }

    public boolean cannotChangeStatus() {
        return !canChangeStatus();
    }

    public void finish() {
        if (cannotChangeStatus()) {
            throw new DomainException("Delivery cannot be finalized");
        }

        setStatus(DeliveryStatus.FINISHED);
        setFinalizationDate(OffsetDateTime.now());
    }

    public void cancel() {
        if (cannotChangeStatus()) {
            throw new DomainException("Delivery cannot be canceled");
        }

        setStatus(DeliveryStatus.CANCELED);
        setFinalizationDate(OffsetDateTime.now());
    }

}
