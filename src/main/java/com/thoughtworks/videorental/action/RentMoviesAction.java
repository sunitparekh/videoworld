package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.Transaction;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.repository.TransactionRepository;
import com.thoughtworks.videorental.interceptor.CustomerAware;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;

import java.util.LinkedHashSet;
import java.util.Set;

public class RentMoviesAction extends ActionSupport implements CustomerAware {

    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;
    private final TransactionRepository transactionRepository;

    private Customer customer;
    private String statement;
    private String[] movieNames;
    private int rentalDuration;

    public RentMoviesAction(final MovieRepository movieRepository, final RentalRepository rentalRepository,
                            final TransactionRepository transactionRepository) {
        this.movieRepository = movieRepository;
        this.rentalRepository = rentalRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public void setMovieNames(final String[] movieNames) {
        this.movieNames = movieNames;
    }

    public void setRentalDuration(final int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public String getStatement() {
        return statement;
    }

    @Override
    public String execute() throws Exception {
        final Set<Movie> movies = movieRepository.withTitles(movieNames);
        LocalDateTime now = new LocalDateTime();
        final Period rentalPeriod = Period.days(rentalDuration);

        final Set<Rental> rentals = new LinkedHashSet<Rental>();
        for (final Movie movie : movies) {
            final Rental rental = new Rental(customer, movie, rentalPeriod, now);
            rentals.add(rental);
        }

        rentalRepository.add(rentals);
        final Transaction transaction = new Transaction(now, customer, rentals);
        transactionRepository.add(transaction);

        statement = customer.statement(transaction.getRentals());
        return SUCCESS;
    }

}
