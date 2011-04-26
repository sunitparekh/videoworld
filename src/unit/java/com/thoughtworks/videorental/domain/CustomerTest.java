package com.thoughtworks.videorental.domain;

import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class CustomerTest {

    private static final String RESOURCES_PATH = "src/unit/resources";
    private static final Set<Rental> EMPTY_RENTALS = Collections.emptySet();

    private Customer customer;
    private Set<Rental> mixedRentals;

    @Before
    public void setUp() {
        customer = new Customer("John Smith");

        final Movie montyPython = new Movie("Monty Python and the Holy Grail");
        final Movie ran = new Movie("Ran");
        final Movie laConfidential = new Movie("LA Confidential");
        final Movie starTrek = new Movie("Star Trek 13.2");
        final Movie WallaceAndGromit = new Movie("Wallace and Gromit");

        mixedRentals = new LinkedHashSet<Rental>();
        LocalDateTime rentedOn = new LocalDateTime();
        mixedRentals.add(new Rental(customer, montyPython, Period.days(3), rentedOn));
        mixedRentals.add(new Rental(customer, ran, Period.days(1), rentedOn));
        mixedRentals.add(new Rental(customer, laConfidential, Period.days(2), rentedOn));
        mixedRentals.add(new Rental(customer, starTrek, Period.days(1), rentedOn));
        mixedRentals.add(new Rental(customer, WallaceAndGromit, Period.days(6), rentedOn));
    }

    @Test
    public void testEmpty() throws Exception {
        String noRentalsStatement =
                "Rental Record for John Smith\n"
                        + "Amount charged is $0.0";
        assertEquals(noRentalsStatement, customer.statement(EMPTY_RENTALS));
    }

    @Test
    public void testCustomer() throws Exception {
        String expected =
                "Rental Record for John Smith\n"
                        + "  Monty Python and the Holy Grail  -  $3.0\n"
                        + "  Ran  -  $1.0\n"
                        + "  LA Confidential  -  $2.0\n"
                        + "  Star Trek 13.2  -  $1.0\n"
                        + "  Wallace and Gromit  -  $6.0\n"
                        + "Amount charged is $13.0";
        assertEquals(expected, customer.statement(mixedRentals));
    }

}
