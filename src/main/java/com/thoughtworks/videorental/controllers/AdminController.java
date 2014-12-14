package com.thoughtworks.videorental.controllers;

import com.thoughtworks.videorental.Views;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by srideep on 14/12/14.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private CustomerRepository customerRepository;

    @Autowired
    public AdminController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "users",method = RequestMethod.GET)
    public ModelAndView getUsers(){
        return Views.USER_ADMIN.prepareView(customerRepository.selectAll());
    }
}
