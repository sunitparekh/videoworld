package com.thoughtworks.videorental.action;

import com.opensymphony.xwork2.Action;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class LoginActionTest {

    private CustomerRepository customerRepository;
    private LoginAction loginAction;

    @Before
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        loginAction = new LoginAction(customerRepository);
    }

    @Test
    public void shouldReturnLoginWhenProvidedNoCustomerName() throws Exception {
        assertEquals(Action.LOGIN, loginAction.execute());
    }
}
