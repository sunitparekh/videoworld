package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Transaction;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;
import com.thoughtworks.videorental.domain.repository.TransactionRepository;
import com.thoughtworks.videorental.domain.specification.TransactionsForCustomerSpecification;
import com.thoughtworks.videorental.domain.specification.TransactionsOrderedByDateTimeComparator;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class SetBasedTransactionRepository extends SetBasedRepository<Transaction> implements TransactionRepository {

    public SetBasedTransactionRepository() {
        super();
    }

    @Override
    public Collection<Transaction> transactionsBy(Customer customer) {
        return selectSatisfying(new TransactionsForCustomerSpecification(customer),
                new TransactionsOrderedByDateTimeComparator());
    }
}
