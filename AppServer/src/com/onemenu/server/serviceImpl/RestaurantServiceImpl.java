package com.onemenu.server.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.dao.RestaurantDAO;
import com.onemenu.server.javabean.bean.RestaurantBean;
import com.onemenu.server.javabean.condition.RestaurantQueryCondition;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.RestaurantService;

@Service
public class RestaurantServiceImpl extends AbstractServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDAO mRestuarntDAO;

    @Override
    @Transactional
    public boolean updateRestaurantStatus(long id, Integer status) {

        boolean result = false;

        Restaurant restaurant = getBaseDAO().findById(Restaurant.class, id);
        restaurant.setmStatus(status);
        getBaseDAO().update(restaurant);

        result = true;

        return result;
    }

    @Override
    public List<RestaurantBean> getRestaurantListByCondition(RestaurantQueryCondition condition) {

        List<Restaurant> restlist = mRestuarntDAO.getRestaurantListByCondition(condition);

        List<RestaurantBean> restaurantBeanList = new ArrayList<RestaurantBean>();

        for (Restaurant restaurant : restlist) {
            RestaurantBean restaurantBean = new RestaurantBean(restaurant);
            restaurantBeanList.add(restaurantBean);
        }

        return restaurantBeanList;

    }

    @Override
    @Transactional
    public boolean approveRestaurantById(long restId) {

        return mRestuarntDAO.updateRestaurantStatus(restId,
                ParameterConstant.RESTAURANT_STATUS_ENABLE);
    }

    @Override
    @Transactional
    public boolean rejectRestaurantById(long restId) {

        return mRestuarntDAO.updateRestaurantStatus(restId,
                ParameterConstant.RESTAURANT_STATUS_DISABLE);
    }

    @Override
    public RestaurantBean getRestaurantBeanById(long restId) {

        Restaurant restaurant = mRestuarntDAO.findById(Restaurant.class, restId);
        RestaurantBean restaurantBean = new RestaurantBean(restaurant);

        return restaurantBean;
    }

}
