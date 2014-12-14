package com.thoughtworks.videorental;

import org.springframework.web.servlet.ModelAndView;

public enum  Views {
    LOGIN_PAGE("login", "customers"), HOME_PAGE("home", "movies"), RENTAL_SUCCESS("statement", "statement"), RENTAL_CURRENT("rentals","rentals"),RENTAL_HISTORY("history","transactions"),USER_ADMIN("admin","users");

    private String viewTemplate;
    private String modelAttribute;

    private Views(String viewTemplate, String modelAttribute){
        this.viewTemplate = viewTemplate;
        this.modelAttribute = modelAttribute;
    };

    public ModelAndView prepareView(Object dataToRender){
        return new ModelAndView(this.viewTemplate, this.modelAttribute, dataToRender);
    }
}
