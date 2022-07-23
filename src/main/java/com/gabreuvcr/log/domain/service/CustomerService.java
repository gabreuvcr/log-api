package com.gabreuvcr.log.domain.service;

import com.gabreuvcr.log.domain.exception.DomainException;
import com.gabreuvcr.log.domain.model.Customer;
import com.gabreuvcr.log.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }

    @Transactional Customer search(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new DomainException("Customer not found"));
    }

    @Transactional
    public Customer save(Customer customer) {
        boolean emailAlreadyUsed = customerRepository.findByEmail(customer.getEmail())
                .stream()
                .anyMatch(c -> !c.equals(customer));

        if (emailAlreadyUsed) {
            throw new DomainException("Email already used.");
        }

        return customerRepository.save(customer);
    }

    @Transactional
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

}
