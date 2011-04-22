package com.thoughtworks.videorental.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class NewReleasePriceTest {
    private static final Price NEW_RELEASE_PRICE = new NewReleasePrice();

    @Test
    public void shouldCalculateCorrectChargeForNewReleaseMovie() throws Exception {
        assertEquals(3.0, NEW_RELEASE_PRICE.getCharge(1));
        assertEquals(6.0, NEW_RELEASE_PRICE.getCharge(2));
        assertEquals(9.0, NEW_RELEASE_PRICE.getCharge(3));
    }
}
