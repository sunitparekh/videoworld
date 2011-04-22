package com.thoughtworks.videorental.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class RegularPriceTest {
    private static final RegularPrice REGULAR_PRICE = new RegularPrice();

    @Test
    public void shouldCalculateCorrectChargeForRegularMovie() throws Exception {
        assertEquals(2.0, REGULAR_PRICE.getCharge(1));
        assertEquals(2.0, REGULAR_PRICE.getCharge(2));
        assertEquals(3.5, REGULAR_PRICE.getCharge(3));
        assertEquals(5.0, REGULAR_PRICE.getCharge(4));
    }
}
