package com.thoughtworks.videorental.domain.specification;


import com.thoughtworks.videorental.domain.Customer;

public class CustomerWithNameSpecification implements Specification<Customer> {

    private final String customerName;

    public CustomerWithNameSpecification(final String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean isSatisfiedBy(final Customer customer) {
        return customer.getName().equals(customerName);
    }


}
