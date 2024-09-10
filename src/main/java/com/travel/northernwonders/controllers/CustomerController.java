package com.travel.northernwonders.controllers;

import com.travel.northernwonders.domain.customer.Customer;
import com.travel.northernwonders.domain.customer.CustomerDTO;
import com.travel.northernwonders.domain.customer.CustomerUserDTO;
import com.travel.northernwonders.services.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCustomerByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(customerService.findCustomerByUserId(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while retrieving Customer: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid CustomerUserDTO request) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomerWithUser(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while creating Customer/User: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerDTO customerDTO) {
        try {
            customerService.updateCustomer(id, customerDTO);
            return ResponseEntity.ok("Customer updated successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error while update customer " + e.getMessage());
        }
    }
}
