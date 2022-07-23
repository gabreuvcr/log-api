package com.gabreuvcr.log.api.mapper;

import com.gabreuvcr.log.api.model.dto.DeliveryInput;
import com.gabreuvcr.log.api.model.dto.DeliveryOutput;
import com.gabreuvcr.log.domain.model.Delivery;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeliveryMapper {

    private ModelMapper modelMapper;

    public DeliveryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DeliveryOutput toDTO(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryOutput.class);
    }

    public List<DeliveryOutput> toCollectionDTO(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryInput deliveryInput) {
        return modelMapper.map(deliveryInput, Delivery.class);
    }
}
