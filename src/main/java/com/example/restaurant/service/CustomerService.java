package com.example.restaurant.service;

import com.example.restaurant.dto.CustomerRequest;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.mapper.CustomerMapper;
import com.example.restaurant.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }

}
