package com.gabreuvcr.log.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabreuvcr.log.domain.model.Delivery;
import com.gabreuvcr.log.domain.model.Occurrence;

@Service
public class OccurrenceService {
    
    private final DeliveryService deliveryService;    

    public OccurrenceService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Transactional
    public Occurrence create(Long id, String description) {
        Delivery delivery = deliveryService.search(id);
        
        Occurrence occurrence = delivery.addOccurrence(description);

        return occurrence;
    }
}
