package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.model.OrderForm;

public interface DeliveryController extends AbstractController {

    public ModelAndView showCallDelivery(HttpSession session);

    public boolean confirmOrderFormByRestaurant(HttpSession session, OrderForm orderForm);
    
    public boolean callDeliveryForOrderForm(HttpSession session, OrderForm orderForm, int minInterval);
    
    public boolean cancelOrderFormByRestaurant(HttpSession session, OrderForm orderForm);
    
    public Object getPendingOrderFormList(HttpSession session) throws Exception;
    
    public boolean addThirdPartyOrderForm(HttpSession session, OrderForm orderForm, int minInterval);

}
