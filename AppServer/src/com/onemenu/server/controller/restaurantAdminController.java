package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;

import com.onemenu.server.model.Restaurant;

/**
 * 
 * @author simonliu
 *
 */
public interface restaurantAdminController extends AbstractController {

    public Object approveRestaurant(String restaurantListStr);

    public boolean rejectRestaurant(String idListStr);
    
    public boolean approveRestaurantById(HttpSession session, Restaurant restaurant);
    
    public boolean rejectRestaurantById(HttpSession session, Restaurant restaurant);
    
    public Object getRestaurantById(HttpSession session, Restaurant restaurant) throws Exception;
}
