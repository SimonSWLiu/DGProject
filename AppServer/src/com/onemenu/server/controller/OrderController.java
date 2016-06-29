package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.model.OrderForm;

public interface OrderController extends AbstractController {

    public ModelAndView showOrderFormList(HttpSession session);

    public boolean confirmOrderFormByRestaurant(HttpSession session, OrderForm orderForm);
    
    public boolean cancelOrderForm(HttpSession session, OrderForm orderForm);
    
    public Object getOrderForm(HttpSession session, long orderFromId) throws Exception;

}
