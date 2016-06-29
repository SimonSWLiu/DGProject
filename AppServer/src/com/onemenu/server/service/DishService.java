package com.onemenu.server.service;

import java.util.List;
import java.util.Map;

import com.onemenu.server.javabean.bean.DishBean;
import com.onemenu.server.javabean.bean.MenuItemDetailBean;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.Restaurant;

public interface DishService extends AbstractServiceInf {

    public Map<String, List<MenuItemDetailBean>> getMenuItemMap(Restaurant restaurant);

    public boolean disableDish(long dishId);

    public boolean enableDish(long dishId);
    
    public List<DishBean> getDishBeanList(Restaurant restaurant);
    
    public Dish getDishById(long dishId);
}
