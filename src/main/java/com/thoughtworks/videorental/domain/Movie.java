package com.thoughtworks.videorental.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Movie {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getCharge(final int daysRented) {
        return daysRented * 1;
    }


}
