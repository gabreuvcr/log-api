package com.gabreuvcr.log.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabreuvcr.log.api.mapper.OccurrenceMapper;
import com.gabreuvcr.log.api.model.dto.OccurrenceInput;
import com.gabreuvcr.log.api.model.dto.OccurrenceOutput;
import com.gabreuvcr.log.domain.model.Delivery;
import com.gabreuvcr.log.domain.model.Occurrence;
import com.gabreuvcr.log.domain.service.DeliveryService;
import com.gabreuvcr.log.domain.service.OccurrenceService;

@RestController
@RequestMapping("deliveries/{id}/occurrences")
public class OccurrenceController {
    
    private final OccurrenceService occurrenceService;
    private final DeliveryService deliveryService;
    private final OccurrenceMapper occurrenceMapper;

    public OccurrenceController(OccurrenceService occurrenceService, DeliveryService deliveryService,
            OccurrenceMapper occurrenceMapper) {
        this.occurrenceService = occurrenceService;
        this.deliveryService = deliveryService;
        this.occurrenceMapper = occurrenceMapper;
    }

    @GetMapping
    public List<OccurrenceOutput> findAll(@PathVariable Long id) {
        Delivery delivery = deliveryService.search(id);

        return occurrenceMapper.toCollectionDTO(delivery.getOccurrences());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceOutput create(@PathVariable Long id, 
            @Valid @RequestBody OccurrenceInput occurrenceInput) {
        
        String description = occurrenceInput.getDescription();
        Occurrence occurrenceCreated = occurrenceService.create(id, description);
        return occurrenceMapper.toDTO(occurrenceCreated);
    }

}
