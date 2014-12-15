package com.thoughtworks.videorental.config.security;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.NonUniqueObjectSelectedException;
import com.thoughtworks.videorental.domain.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by srideep on 14/12/14.
 */

public class UserAuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private CustomerRepository customerRepository;

    @Autowired
    public UserAuthenticationProvider(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        Customer customer;
        try {
            customer = customerRepository.selectUnique(new Specification<Customer>() {
                @Override
                public boolean isSatisfiedBy(Customer object) {
                    return object.getName().equalsIgnoreCase(authentication.getName());
                }
            });
        } catch (NonUniqueObjectSelectedException e) {
            throw new AuthenticationServiceException("Duplicate customer entries found - cannot authenticate", e);
        }

        if (customer == null){
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(new Customer(authentication.getName()), authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
