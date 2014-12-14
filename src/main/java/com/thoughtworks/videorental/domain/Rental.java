package com.thoughtworks.videorental.domain;

import lombok.EqualsAndHashCode;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

@EqualsAndHashCode
public class Rental {
    private final Movie movie;
    private final Customer customer;
    private final LocalDateTime rentedOn;
    private final Period period;

    public Rental(Customer customer, Movie movie, Period period, LocalDateTime rentedOn) {
        this.movie = movie;
        this.customer = customer;
        this.period = period;
        this.rentedOn = rentedOn;
    }

    public Movie getMovie() {
        return movie;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Period getPeriod() {
        return period;
    }

    public LocalDateTime getRentedOn() {
        return rentedOn;
    }

    public LocalDateTime getEndDate() {
        return rentedOn.plus(period);
    }
}