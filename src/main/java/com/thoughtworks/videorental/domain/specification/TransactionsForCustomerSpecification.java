package com.thoughtworks.videorental.domain.specification;


import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Transaction;

public class TransactionsForCustomerSpecification implements Specification<Transaction> {
    private final Customer customer;

    public TransactionsForCustomerSpecification(final Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean isSatisfiedBy(final Transaction transaction) {
        return customer.equals(transaction.getCustomer());
    }

}
