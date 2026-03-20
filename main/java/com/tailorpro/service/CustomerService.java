package com.tailorpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tailorpro.dto.CustomerDTO;
import com.tailorpro.entity.Customer;
import com.tailorpro.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerDTO addCustomer(CustomerDTO dto) {
        Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        customer.setGender(dto.getGender());
        customer.setEmail(dto.getEmail());

        Customer saved = customerRepository.save(customer);

        return new CustomerDTO(
                saved.getCustomerId(),
                saved.getName(),
                saved.getPhoneNumber(),
                saved.getAddress(),
                saved.getGender(),
                saved.getEmail()
        );
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(customer ->
                new CustomerDTO(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getPhoneNumber(),
                        customer.getAddress(),
                        customer.getGender(),
                        customer.getEmail()
                )
        ).toList();
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        return new CustomerDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getGender(),
                customer.getEmail()
        );
    }
    
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElseThrow();

        customer.setName(updatedCustomer.getName());
        customer.setPhoneNumber(updatedCustomer.getPhoneNumber());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setGender(updatedCustomer.getGender());
        customer.setEmail(updatedCustomer.getEmail());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
