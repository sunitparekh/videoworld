package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;
import com.thoughtworks.videorental.domain.specification.Specification;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public class SetBasedCustomerRepository extends SetBasedRepository<Customer> implements CustomerRepository {

    public SetBasedCustomerRepository(final Collection<Customer> entities) {
        super(entities);
    }

    @Override
    public Set<Customer> selectAll(Comparator<Customer> comparator) {
        return selectAll((Comparator<Customer>) comparator);
    }

    @Override
    public Set<Customer> selectSatisfying(Specification<Customer> specification, Comparator<Customer> comparator) {
        return selectSatisfying(specification, (Comparator<Customer>) comparator);
    }

}
