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
    public ModelAndView login() throws Exception {
        return Views.LOGIN_PAGE.prepareView(customerRepository.selectAll(new CustomersOrderedByNameComparator()));
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public String login() throws Exception {
//        if (!StringUtils.hasText(customerName)) {
//            return Views.LOGIN_PAGE.getViewTemplate();
//        }
//
//        Customer loggedInCustomer = customerRepository.selectUnique(new CustomerWithNameSpecification(customerName));
//        if (loggedInCustomer == null) {
//            return Views.LOGIN_PAGE.getViewTemplate();
//        }

//

//        System.out.println("REDIRECT");
//        return Views.HOME_PAGE.getRedirectUrl();
//    }

}
