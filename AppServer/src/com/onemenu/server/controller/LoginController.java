package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.model.Account;
import com.onemenu.server.model.Restaurant;


public interface LoginController extends AbstractController {

    public ModelAndView showLoginPage(HttpSession session);

    public ModelAndView showAdminLoginPage(HttpSession session);
    
    public Object validateLogin(HttpSession session);

    public ModelAndView login(HttpSession session, Account account) throws Exception;

    public ModelAndView adminLogin(HttpSession session, Account account) throws Exception;
    
    public ModelAndView logout(HttpSession session);

    public ModelAndView showMerchantRegistrationPage(HttpSession session);

    public ModelAndView showRestaurantRegistrationPage(HttpSession session);

    public ModelAndView merchantRegister(HttpSession session, Account account);

    public ModelAndView registerRestaurant(HttpSession session, Restaurant restaurant,
            String base64Str, String width, String height) throws Exception;

}
