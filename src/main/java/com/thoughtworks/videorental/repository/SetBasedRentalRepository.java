package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;
import com.thoughtworks.videorental.domain.specification.AndSpecification;
import com.thoughtworks.videorental.domain.specification.CurrentRentalSpecification;
import com.thoughtworks.videorental.domain.specification.RentalForCustomerSpecification;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class SetBasedRentalRepository extends SetBasedRepository<Rental> implements RentalRepository {

    public SetBasedRentalRepository() {
        super();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<Rental> currentRentalsFor(final Customer customer) {
        return selectSatisfying(new AndSpecification<Rental>(new RentalForCustomerSpecification(customer),
                new CurrentRentalSpecification()));
    }
}
