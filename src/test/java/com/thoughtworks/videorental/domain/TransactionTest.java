package com.thoughtworks.videorental.domain;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class TransactionTest {
    private static final Movie FINDING_NEMO = new Movie("Finding Nemo");
    private static final Movie SHAWSHANK_REDEMPTION = new Movie("Shawshank Redemption");

    private static final Customer CUSTOMER_ONE = new Customer("James Cameron");
    private static final Customer CUSTOMER_TWO = new Customer("Quentin Tarantino");

    private static final Rental RENTAL_ONE = new Rental(CUSTOMER_ONE, FINDING_NEMO, Period.days(1), new LocalDateTime());
    private static final Rental RENTAL_TWO = new Rental(CUSTOMER_ONE, SHAWSHANK_REDEMPTION, Period.days(3), new LocalDateTime());

    @Test
    public void shouldReturnDifferentRentalSetFromConstruction() {
        final Set<Rental> rentals = new HashSet<Rental>();
        rentals.add(RENTAL_ONE);
        final Transaction transaction = new Transaction(new LocalDateTime(), CUSTOMER_ONE, rentals);

        rentals.add(RENTAL_TWO);

        assertFalse(rentals.equals(transaction.getRentals()));
        assertEquals(Collections.singleton(RENTAL_ONE), transaction.getRentals());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnUnmodifiableRentalSet() {
        final Transaction transaction = new Transaction(new LocalDateTime(), CUSTOMER_ONE, Collections.singleton(RENTAL_ONE));
        transaction.getRentals().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfRentalForDifferentCustomer() {
        new Transaction(new LocalDateTime(), CUSTOMER_TWO, Collections.singleton(RENTAL_ONE));
    }
}
