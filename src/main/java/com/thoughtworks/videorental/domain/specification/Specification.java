package com.thoughtworks.videorental.domain.specification;

public interface Specification<T> {

    boolean isSatisfiedBy(T object);
}
