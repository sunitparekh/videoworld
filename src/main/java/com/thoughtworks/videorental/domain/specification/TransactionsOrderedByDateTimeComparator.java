package com.thoughtworks.videorental.domain.specification;


import com.thoughtworks.videorental.domain.Transaction;

import java.util.Comparator;

public class TransactionsOrderedByDateTimeComparator implements Comparator<Transaction> {

    @Override
    public int compare(final Transaction transaction1, final Transaction transaction2) {
        return transaction1.getDateTime().compareTo(transaction2.getDateTime());
    }

}
