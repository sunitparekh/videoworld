package com.thoughtworks.videorental.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Transaction;
import com.thoughtworks.videorental.domain.repository.SetBasedRepository;
import com.thoughtworks.videorental.domain.repository.TransactionRepository;
import com.thoughtworks.videorental.domain.specification.Specification;
import com.thoughtworks.videorental.domain.specification.TransactionsForCustomerSpecification;
import com.thoughtworks.videorental.domain.specification.TransactionsOrderedByDateTimeComparator;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public class SetBasedTransactionRepository extends SetBasedRepository<Transaction> implements TransactionRepository {

    public SetBasedTransactionRepository() {
        super();
    }

    @Override
    public Set<Transaction> selectAll(Comparator<Transaction> comparator) {
        return selectAll((Comparator<Transaction>) comparator);
    }

    @Override
    public Set<Transaction> selectSatisfying(final Specification<Transaction> specification,
                                             final Comparator<Transaction> comparator) {
        return selectSatisfying(specification, (Comparator<Transaction>) comparator);
    }

    @Override
    public Collection<Transaction> transactionsBy(Customer customer) {
        return selectSatisfying(new TransactionsForCustomerSpecification(customer),
                new TransactionsOrderedByDateTimeComparator());
    }
}
