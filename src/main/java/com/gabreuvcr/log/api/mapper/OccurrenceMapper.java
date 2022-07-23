package com.gabreuvcr.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gabreuvcr.log.api.model.dto.OccurrenceOutput;
import com.gabreuvcr.log.domain.model.Occurrence;

@Component
public class OccurrenceMapper {
    
    private final ModelMapper modelMapper;

    public OccurrenceMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public OccurrenceOutput toDTO(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceOutput.class);
    }

    public List<OccurrenceOutput> toCollectionDTO(List<Occurrence> occurrences) {
        return occurrences.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
}
