package com.thoughtworks.videorental.controllers;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.specification.CustomersOrderedByNameComparator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by srideep on 14/12/14.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    private LoginController sut;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setup(){
        sut = new LoginController(customerRepository);
    }

    @Test
    public void login_shouldReturnModelAndViewForLoginPage(){

        Set<Customer> customerList = new HashSet<Customer>();
        when(customerRepository.selectAll(any(CustomersOrderedByNameComparator.class))).thenReturn(customerList); //here any() is OK since the class does not really have state

        ModelAndView loginModelAndView = sut.login();

        assertSame(customerList, loginModelAndView.getModel().get("customers"));
        assertSame("login", loginModelAndView.getViewName());

        verify(customerRepository).selectAll(any(CustomersOrderedByNameComparator.class));

    }
}
