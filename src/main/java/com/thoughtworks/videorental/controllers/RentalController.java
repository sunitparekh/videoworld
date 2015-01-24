package com.thoughtworks.videorental.controllers;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.thoughtworks.videorental.Views;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.Transaction;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.repository.TransactionRepository;
import com.thoughtworks.videorental.utils.DateTimeProvider;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * Created by srideep on 13/12/14.
 */
@Controller
@RequestMapping("/rentals")
public class RentalController {
    private final MovieRepository movieRepository;
    private final RentalRepository rentalRepository;
    private final TransactionRepository transactionRepository;
    private final DateTimeProvider dateTimeProvider;

    @Autowired
    public RentalController(final MovieRepository movieRepository, final RentalRepository rentalRepository,
                            final TransactionRepository transactionRepository, DateTimeProvider dateTimeProvider) {
        this.movieRepository = movieRepository;
        this.rentalRepository = rentalRepository;
        this.transactionRepository = transactionRepository;
        this.dateTimeProvider = dateTimeProvider;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView rent(@RequestParam("movieNames") String[] movieNames, @RequestParam("rentalDuration") int rentalDuration, Authentication authInfo){
        final Customer customer = (Customer) authInfo.getPrincipal();
        final Set<Movie> movies = movieRepository.withTitles(movieNames);
        final LocalDateTime now = dateTimeProvider.getLocalDateTime();
        final Period rentalPeriod = Period.days(rentalDuration);

        final Set<Rental> rentals = Sets.newHashSet(Iterables.transform(movies, new Function<Movie, Rental>() {
            @Override
            public Rental apply(Movie movie) {
                return new Rental(customer, movie, rentalPeriod, now);
            }
        }));

        rentalRepository.add(rentals);

        final Transaction transaction = new Transaction(now, customer, rentals);
        transactionRepository.add(transaction);

        return Views.RENTAL_SUCCESS.prepareView(customer.statement(transaction.getRentals()));

    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView currentRentals(Authentication authInfo){
        return Views.RENTAL_CURRENT.prepareView(rentalRepository.currentRentalsFor((Customer) authInfo.getPrincipal()));
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public ModelAndView history(Authentication authInfo){
        return Views.RENTAL_HISTORY.prepareView(transactionRepository.transactionsBy((Customer) authInfo.getPrincipal()));
    }

}
