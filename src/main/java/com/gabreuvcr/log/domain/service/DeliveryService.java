package com.gabreuvcr.log.domain.service;

import com.gabreuvcr.log.domain.exception.NotFoundException;
import com.gabreuvcr.log.domain.model.Customer;
import com.gabreuvcr.log.domain.model.Delivery;
import com.gabreuvcr.log.domain.model.DeliveryStatus;
import com.gabreuvcr.log.domain.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CustomerService customerService;

    public DeliveryService(DeliveryRepository deliveryRepository, CustomerService customerService) {
        this.deliveryRepository = deliveryRepository;
        this.customerService = customerService;
    }

    @Transactional
    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    @Transactional
    public Optional<Delivery> findById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Transactional
    public Delivery search(Long id) {
        return deliveryRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Delivery not found."));
    }

    @Transactional
    public Delivery create(Delivery delivery) {
        Customer customer = customerService.search(delivery.getCustomer().getId());

        delivery.setCustomer(customer);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }

    @Transactional
    public void finish(Long id) {
        Delivery delivery = search(id);

        delivery.finish();

        deliveryRepository.save(delivery);
    }

    @Transactional
    public void cancel(Long id) {
        Delivery delivery = search(id);

        delivery.cancel();

        deliveryRepository.save(delivery);
    }

}
