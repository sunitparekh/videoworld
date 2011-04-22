package com.thoughtworks.videorental.domain.repository;

public class NonUniqueObjectSelectedException extends Exception {
    public NonUniqueObjectSelectedException() {
    }

    public NonUniqueObjectSelectedException(final Exception cause) {
        super(cause);
    }

}
