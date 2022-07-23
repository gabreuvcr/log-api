package com.gabreuvcr.log.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.gabreuvcr.log.api.model.dto.CustomerInput;
import com.gabreuvcr.log.api.model.dto.CustomerOutput;
import com.gabreuvcr.log.domain.model.Customer;

@Component
public class CustomerMapper {
    
    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CustomerOutput toDTO(Customer customer) {
        return modelMapper.map(customer, CustomerOutput.class);
    }

    public List<CustomerOutput> toCollectionDTO(List<Customer> customers) {
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Customer toEntity(CustomerInput customerInput) {
        return modelMapper.map(customerInput, Customer.class);
    }
}
