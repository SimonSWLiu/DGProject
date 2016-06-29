package com.onemenu.server.dao;

import com.onemenu.server.model.Dish;


public interface DishDAO extends BaseDAOInf {

    public boolean updateDishStatus(long dishId, String status)  throws RuntimeException;
    
    public Dish getDishById(long dishId);
    
}
