package com.example.restaurant.service;

import com.example.restaurant.dto.CustomerRequest;
import com.example.restaurant.dto.CustomerResponse;
import com.example.restaurant.dto.LoginRequest;
import com.example.restaurant.entity.Customer;
import com.example.restaurant.exception.CustomerNotFoundException;
import com.example.restaurant.mapper.CustomerMapper;
import com.example.restaurant.repo.CustomerRepo;
import com.example.restaurant.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

import static java.lang.String.format;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toEntity(request);
        customerRepo.save(customer);
        return "Created";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return CustomerMapper.toCustomerResponse(customer);
    }
    public String login(LoginRequest request) {
        // Retrieve the customer object using the provided email
        Customer customer = getCustomer(request.email());

        // Validate the password directly (plaintext comparison)
        if (!request.password().equals(customer.getPassword())) {
            return "Wrong Password or Email";
        }

        // Generate and return a JWT token if validation is successful
        return "Validated";
    }


}
