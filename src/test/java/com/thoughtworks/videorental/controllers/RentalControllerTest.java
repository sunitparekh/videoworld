package com.thoughtworks.videorental.controllers;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

/**
 * Created by srideep on 14/12/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class RentalControllerTest {

    private RentalController sut;

    @Mock
    private MovieRepository movieRepository;
    @Mock
    private RentalRepository rentalRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private DateTimeProvider dateTimeProvider;
    @Mock
    private Customer customer;
    @Mock
    private Authentication authInfo;

    @Before
    public void setup(){
        when(authInfo.getPrincipal()).thenReturn(customer);
        sut = new RentalController(movieRepository, rentalRepository, transactionRepository, dateTimeProvider);
    }


    @Test
    public void rent_shouldAddMovieToRentalRepository(){
        final String[] movies = new String[]{"movie1"};
        final Movie movie1 = new Movie("movie1");
        final Set<Movie> moviesSet = Sets.newSet(movie1);
        final LocalDateTime dateTime = new LocalDateTime();
        final Set<Rental> expectedRentals = com.google.common.collect.Sets.newHashSet(new Rental(customer,movie1,Period.days(1),dateTime));

        when(authInfo.getPrincipal()).thenReturn(customer);
        when(movieRepository.withTitles(movies)).thenReturn(moviesSet);
        when(dateTimeProvider.getLocalDateTime()).thenReturn(dateTime);

        sut.rent(movies, 1 , authInfo);

        verify(rentalRepository).add(eq(expectedRentals));
    }

    @Test
    public void rent_shouldAddRentalToTransactionsRepository(){
        final LocalDateTime dateTime = new LocalDateTime();
        final String[] movies = new String[]{"movie1"};
        final Movie movie1 = new Movie("movie1");
        final Set<Movie> moviesSet = Sets.newSet(movie1);
        final Set<Rental> expectedRentals = com.google.common.collect.Sets.newHashSet(new Rental(customer,movie1,Period.days(1),dateTime));
        final Transaction expectedTransaction = new Transaction(dateTime,customer,expectedRentals);

        when(movieRepository.withTitles(movies)).thenReturn(moviesSet);
        when(dateTimeProvider.getLocalDateTime()).thenReturn(dateTime);

        sut.rent(movies, 1 , authInfo);

        verify(transactionRepository).add(eq(expectedTransaction));
    }

    @Test
    public void rent_ShouldRenderViewWithRentalStatementOnSuccess(){
        final LocalDateTime dateTime = new LocalDateTime();
        final String[] movies = new String[]{"movie1"};
        final Movie movie1 = new Movie("movie1");
        final Set<Movie> moviesSet = Sets.newSet(movie1);
        final Set<Rental> expectedRentals = com.google.common.collect.Sets.newHashSet(new Rental(customer, movie1, Period.days(1), dateTime));
        final String expectedRentalStatement = "someRentalStatement";

        when(movieRepository.withTitles(movies)).thenReturn(moviesSet);
        when(dateTimeProvider.getLocalDateTime()).thenReturn(dateTime);
        when(customer.statement(eq(expectedRentals))).thenReturn(expectedRentalStatement);

        final ModelAndView modelAndView = sut.rent(movies, 1, authInfo);

        assertSame("statement",modelAndView.getViewName());
        assertSame(expectedRentalStatement,modelAndView.getModel().get("statement"));

        verify(customer).statement(eq(expectedRentals));
    }

    @Test
    public void currentRentals_shouldReturnCurrentRentalsForLoggedInUser(){
        final Set<Rental> rentalsSet = new HashSet<Rental>();

        when(rentalRepository.currentRentalsFor(customer)).thenReturn(rentalsSet);

        final ModelAndView modelAndView = sut.currentRentals(authInfo);

        assertSame(rentalsSet,modelAndView.getModel().get("rentals"));
        assertSame("rentals",modelAndView.getViewName());
    }

    @Test
    public void history_shouldReturnRentalHistoryForLoggedInUser(){
        final Collection<Transaction> expectedTransactions = mock(Collection.class);

        when(transactionRepository.transactionsBy(customer)).thenReturn(expectedTransactions);

        final ModelAndView modelAndView = sut.history(authInfo);

        assertSame("history",modelAndView.getViewName());
        assertSame(expectedTransactions, modelAndView.getModel().get("transactions"));

        verify(transactionRepository).transactionsBy(customer);
    }
}
