package com.thoughtworks.videorental.domain.specification;


import com.thoughtworks.videorental.domain.Rental;
import org.joda.time.LocalDateTime;

public class CurrentRentalSpecification implements Specification<Rental> {

    @Override
    public boolean isSatisfiedBy(final Rental rental) {
        return !rental.getEndDate().isBefore(new LocalDateTime());
    }

}
