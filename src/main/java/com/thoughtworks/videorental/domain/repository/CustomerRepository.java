package com.thoughtworks.videorental.domain.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.specification.Specification;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public interface CustomerRepository {
    void add(Customer entity) throws NullObjectAddedException;

    void add(Collection<Customer> entities) throws NullObjectAddedException;

    Set<Customer> selectAll();

    Set<Customer> selectAll(Comparator<Customer> comparator);

    Set<Customer> selectSatisfying(Specification<Customer> specification);

    Set<Customer> selectSatisfying(Specification<Customer> specification, Comparator<Customer> comparator);

    Customer selectUnique(Specification<Customer> specification) throws NonUniqueObjectSelectedException;
}