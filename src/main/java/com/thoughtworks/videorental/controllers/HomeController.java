package com.thoughtworks.videorental.controllers;

import com.thoughtworks.videorental.Views;
import com.thoughtworks.videorental.domain.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by srideep on 13/12/14.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    private final MovieRepository movieRepository;

    @Autowired
    public HomeController(final MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(){
        return Views.HOME_PAGE.prepareView(movieRepository.selectAll());
    }


}
