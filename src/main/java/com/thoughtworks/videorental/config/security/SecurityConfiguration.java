package com.thoughtworks.videorental.config.security;

import com.thoughtworks.videorental.domain.Customer;
import com.thoughtworks.videorental.domain.repository.CustomerRepository;
import com.thoughtworks.videorental.repository.SetBasedCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import java.util.Arrays;

/**
 * Created by srideep on 14/12/14.
 */
@Configuration
@EnableWebMvcSecurity
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired //TODO: Move this to constructor injection - currently due to cross dependencies causes NPE if constructor injection is used
    private CustomerRepository customerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .and()
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login")
                .and()
                .authorizeRequests()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/styles/**").permitAll()
                    .antMatchers("/**").authenticated();
        http.csrf().disable(); //Forget about CSRF for now
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new UserAuthenticationProvider(this.customerRepository));
    }
}
