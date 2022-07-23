package com.gabreuvcr.log.api.controller;

import com.gabreuvcr.log.api.mapper.DeliveryMapper;
import com.gabreuvcr.log.api.model.dto.DeliveryInput;
import com.gabreuvcr.log.api.model.dto.DeliveryOutput;
import com.gabreuvcr.log.domain.model.Delivery;
import com.gabreuvcr.log.domain.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final DeliveryMapper deliveryMapper;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
        this.deliveryService = deliveryService;
        this.deliveryMapper = deliveryMapper;
    }

    @GetMapping
    public List<DeliveryOutput> findAll() {
        List<Delivery> deliveries = deliveryService.findAll();
        return deliveryMapper.toCollectionDTO(deliveries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOutput> findById(@PathVariable Long id) {
        return deliveryService.findById(id)
            .map(delivery -> {
                DeliveryOutput deliveryDTO = deliveryMapper.toDTO(delivery);
                return ResponseEntity.ok(deliveryDTO);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryOutput create(@Valid  @RequestBody DeliveryInput deliveryInput) {
        Delivery delivery = deliveryMapper.toEntity(deliveryInput);
        Delivery deliveryCreated = deliveryService.create(delivery);
        return deliveryMapper.toDTO(deliveryCreated);
    }

    @PutMapping("/{id}/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long id) {
        deliveryService.finish(id);
    }

    @PutMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable Long id) {
        deliveryService.cancel(id);
    }

}
