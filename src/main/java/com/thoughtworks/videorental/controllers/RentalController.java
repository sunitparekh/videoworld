package com.thoughtworks.videorental.controllers;

import com.thoughtworks.videorental.Views;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.Movie;
import com.thoughtworks.videorental.domain.Rental;
import com.thoughtworks.videorental.domain.Transaction;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import com.thoughtworks.videorental.domain.repository.RentalRepository;
import com.thoughtworks.videorental.domain.repository.TransactionRepository;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedHashSet;
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

    @Autowired
    public RentalController(final MovieRepository movieRepository, final RentalRepository rentalRepository,
                            final TransactionRepository transactionRepository) {
        this.movieRepository = movieRepository;
        this.rentalRepository = rentalRepository;
        this.transactionRepository = transactionRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView rent(@RequestParam("movieNames") String[] movieNames, @RequestParam("rentalDuration") int rentalDuration, Authentication authInfo){
        final Customer customer = (Customer) authInfo.getPrincipal();
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
