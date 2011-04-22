package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;

import java.util.Collection;

public class SetBasedCustomerRepository extends SetBasedRepository<Customer> implements CustomerRepository {

    public SetBasedCustomerRepository(final Collection<Customer> entities) {
        super(entities);
    }
}
