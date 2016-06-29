package com.onemenu.server.service;

import java.util.List;

import com.onemenu.server.javabean.bean.DriverBean;
import com.onemenu.server.javabean.bean.RestaurantBean;
import com.onemenu.server.javabean.condition.RestaurantQueryCondition;

public interface RestaurantService extends AbstractServiceInf {

    public boolean updateRestaurantStatus(long id, Integer status);

    public List<RestaurantBean> getRestaurantListByCondition(RestaurantQueryCondition condition);

    public boolean approveRestaurantById(long restId);

    public boolean rejectRestaurantById(long restId);

    public RestaurantBean getRestaurantBeanById(long restId);

}
