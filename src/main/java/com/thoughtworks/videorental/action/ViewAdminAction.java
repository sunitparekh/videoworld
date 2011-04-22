package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.ActionSupport;
import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;

import java.util.Set;

public class ViewAdminAction extends ActionSupport {

    private final CustomerRepository customerRepository;

    public ViewAdminAction(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Set<Customer> getUsers() {
        return customerRepository.selectAll();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

}
