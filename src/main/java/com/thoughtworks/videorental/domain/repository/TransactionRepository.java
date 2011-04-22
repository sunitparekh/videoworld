package com.thoughtworks.videorental.domain.repository;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Transaction;
import com.thoughtworks.videorental.domain.specification.Specification;

import java.util.Collection;
import java.util.Comparator;
import java.util.Set;

public interface TransactionRepository {
    void add(Transaction entity) throws NullObjectAddedException;

    void add(Collection<Transaction> entities) throws NullObjectAddedException;

    Set<Transaction> selectAll();

    Set<Transaction> selectAll(Comparator<Transaction> comparator);

    Set<Transaction> selectSatisfying(Specification<Transaction> specification);

    Set<Transaction> selectSatisfying(Specification<Transaction> specification, Comparator<Transaction> comparator);

    Transaction selectUnique(Specification<Transaction> specification) throws NonUniqueObjectSelectedException;

    Collection<Transaction> transactionsBy(Customer customer);
}
