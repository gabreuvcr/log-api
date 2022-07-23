package com.gabreuvcr.log.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.gabreuvcr.log.domain.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gabreuvcr.log.api.mapper.CustomerMapper;
import com.gabreuvcr.log.api.model.dto.CustomerInput;
import com.gabreuvcr.log.api.model.dto.CustomerOutput;
import com.gabreuvcr.log.domain.model.Customer;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public List<CustomerOutput> findAll() {
        List<Customer> customers = customerService.findAll();
        return customerMapper.toCollectionDTO(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOutput> findById(@PathVariable Long id) {
        return customerService.findById(id)
            .map(customer -> {
                CustomerOutput customerDTO = customerMapper.toDTO(customer);
                return ResponseEntity.ok(customerDTO);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerOutput create(@Valid @RequestBody CustomerInput customerInput) {
        Customer customer = customerMapper.toEntity(customerInput);
        Customer customerCreated = customerService.save(customer);
        return customerMapper.toDTO(customerCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerOutput> update(@PathVariable Long id, 
            @Valid @RequestBody CustomerInput customerInput) {
        
        if (!customerService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Customer customer = customerMapper.toEntity(customerInput);

        customer.setId(id);
        Customer customerUpdated = customerService.save(customer);
        return ResponseEntity.ok(customerMapper.toDTO(customerUpdated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!customerService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
