package com.onemenu.server.dao;
import java.util.List;

import com.onemenu.server.javabean.condition.RestaurantQueryCondition;
import com.onemenu.server.model.Restaurant;



public interface RestaurantDAO extends BaseDAOInf {

    public List<Restaurant> getRestaurantListByCondition(RestaurantQueryCondition condition);
    
    public boolean updateRestaurantStatus(long restId, Integer status);

}
