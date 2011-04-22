package com.thoughtworks.videorental.domain.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.specification.Specification;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public interface RentalRepository {
    void add(Rental entity) throws NullObjectAddedException;

    void add(Collection<Rental> entities) throws NullObjectAddedException;

    Set<Rental> selectAll();

    Set<Rental> selectAll(Comparator<Rental> comparator);

    Set<Rental> selectSatisfying(Specification<Rental> specification);

    Set<Rental> selectSatisfying(Specification<Rental> specification, Comparator<Rental> comparator);

    Rental selectUnique(Specification<Rental> specification) throws NonUniqueObjectSelectedException;

    Set<Rental> currentRentalsFor(Customer customer);
}