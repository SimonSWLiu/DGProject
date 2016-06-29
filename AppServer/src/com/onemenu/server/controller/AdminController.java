package com.onemenu.server.controller;

import org.springframework.web.servlet.ModelAndView;


public interface AdminController extends AbstractController {

    public ModelAndView showAdminMainPage();
    
    public ModelAndView showRestaurantApprovalPage();
    
    public ModelAndView showDriverApprovalPage();
    
    public ModelAndView showDeliveryTimeSheetPage();

    public ModelAndView showOneMenuConfigPage();
    
    public ModelAndView showRestaurantListPage();
    
    public ModelAndView showDriverListPage();
    
    public ModelAndView showOrderFormListPage();
    
}
