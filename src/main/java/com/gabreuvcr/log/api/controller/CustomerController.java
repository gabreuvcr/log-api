package com.gabreuvcr.log.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabreuvcr.log.domain.model.Customer;

@RestController
public class CustomerController {
    
    @GetMapping("/customers")
    public List<Customer> list() {
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("João");
        customer1.setPhone("31999999999");
        customer1.setEmail("joao@gmail.com");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("Maria");
        customer2.setPhone("31999999998");
        customer2.setEmail("maria@gmail.com");

        return Arrays.asList(customer1, customer2);
    }
}
