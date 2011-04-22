package com.thoughtworks.videorental.domain.specification;


import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Rental;

public class RentalForCustomerSpecification implements Specification<Rental> {

    private final Customer customer;

    public RentalForCustomerSpecification(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean isSatisfiedBy(final Rental rental) {
        return customer.equals(rental.getCustomer());
    }

}
