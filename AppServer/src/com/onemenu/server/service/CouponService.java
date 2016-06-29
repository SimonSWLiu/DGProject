package com.onemenu.server.service;

import java.util.List;

import com.onemenu.server.javabean.bean.CouponBean;
import com.onemenu.server.model.Coupon;

/**
 * 
 * @author simonliu
 *
 */
public interface CouponService extends AbstractServiceInf {

    public List<Coupon> getCouponListByRestaurantId(long restaurantId);
    
    public CouponBean getCouponById(long couponId);

}
