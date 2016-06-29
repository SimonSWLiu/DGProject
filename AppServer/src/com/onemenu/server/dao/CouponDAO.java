package com.onemenu.server.dao;

import java.util.List;

import com.onemenu.server.model.Coupon;


public interface CouponDAO extends BaseDAOInf {
    
    public List<Coupon> getCouponListByRestaurantId(long restaurantId) throws RuntimeException;
    
}
