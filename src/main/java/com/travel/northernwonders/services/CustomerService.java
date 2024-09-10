package com.travel.northernwonders.services;

import com.travel.northernwonders.domain.customer.Customer;
import com.travel.northernwonders.domain.customer.CustomerDTO;
import com.travel.northernwonders.domain.customer.CustomerResponseDTO;
import com.travel.northernwonders.domain.customer.CustomerUserDTO;
import com.travel.northernwonders.domain.user.User;
import com.travel.northernwonders.domain.user.UserResponseDTO;
import com.travel.northernwonders.repositories.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserService userService;

    public CustomerService(CustomerRepository customerRepository, UserService userService) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public CustomerResponseDTO findCustomerByUserId(String userId) {
        Customer customer = this.customerRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found for user ID: " + userId));

        return this.getCustomerResponseDTO(customer);
    }

    @Transactional
    public CustomerResponseDTO createCustomerWithUser(CustomerUserDTO request) {
        User user = userService.createUser(request.user());

        Customer customer = new Customer();
        BeanUtils.copyProperties(request.customer(), customer);
        customer.setUser(user);

        return this.getCustomerResponseDTO(customerRepository.save(customer));
    }

    @Transactional
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + id));

        BeanUtils.copyProperties(customerDTO, existingCustomer);

        customerRepository.save(existingCustomer);
    }

    private CustomerResponseDTO getCustomerResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
                customer.getId(),
                new UserResponseDTO(customer.getUser().getId(), customer.getUser().getLogin()),
                customer.getName(),
                customer.getPhone(),
                customer.getBirthDate(),
                customer.getDocumentNumber(),
                customer.getAddress()
        );
    }
}
