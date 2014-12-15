package com.thoughtworks.videorental.controllers;

import com.thoughtworks.videorental.Views;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.domain.specification.CustomersOrderedByNameComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
* Created by srideep on 11/12/14.
*/

@Controller
@RequestMapping("/login")
public class LoginController {

    private final CustomerRepository customerRepository;

    @Autowired
    public LoginController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login() {
        return Views.LOGIN_PAGE.prepareView(customerRepository.selectAll(new CustomersOrderedByNameComparator()));
    }

}
