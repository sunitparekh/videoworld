package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;
import com.thoughtworks.videorental.domain.specification.AndSpecification;
import com.thoughtworks.videorental.domain.specification.CurrentRentalSpecification;
import com.thoughtworks.videorental.domain.specification.RentalForCustomerSpecification;
import com.thoughtworks.videorental.domain.specification.Specification;

import java.util.Comparator;
import java.util.Set;

public class SetBasedRentalRepository extends SetBasedRepository<Rental> implements RentalRepository {

    public SetBasedRentalRepository() {
        super();
    }

    @Override
    public Set<Rental> selectAll(Comparator<Rental> comparator) {
        return selectAll((Comparator<Rental>) comparator);
    }

    @Override
    public Set<Rental> selectSatisfying(final Specification<Rental> specification,
                                        final Comparator<Rental> comparator) {
        return selectSatisfying(specification, (Comparator<Rental>) comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Rental> currentRentalsFor(final Customer customer) {
        return selectSatisfying(new AndSpecification<Rental>(new RentalForCustomerSpecification(customer),
                new CurrentRentalSpecification()));
    }
}
