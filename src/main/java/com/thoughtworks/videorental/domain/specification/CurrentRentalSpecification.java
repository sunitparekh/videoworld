package com.thoughtworks.videorental.domain.specification;


import com.thoughtworks.videorental.domain.Rental;

public class CurrentRentalSpecification implements Specification<Rental> {

    @Override
    public boolean isSatisfiedBy(final Rental rental) {
        return !rental.getPeriod().getEndDate().isBeforeNow();
    }

}
