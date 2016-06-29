package com.onemenu.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.dao.CouponDAO;
import com.onemenu.server.dao.DishCategoryDAO;
import com.onemenu.server.javabean.bean.CouponBean;
import com.onemenu.server.model.Coupon;
import com.onemenu.server.model.DishCategory;
import com.onemenu.server.service.CouponService;

@Service
public class CouponServiceImpl extends AbstractServiceImpl implements CouponService {

    private CouponDAO couponDAO;
    private DishCategoryDAO dishCategoryDAO;

    @Autowired
    public void setCouponDAO(CouponDAO couponDAO) {
        this.couponDAO = couponDAO;
    }

    @Autowired
    public void setDishCategoryDAO(DishCategoryDAO dishCategoryDAO) {
        this.dishCategoryDAO = dishCategoryDAO;
    }


    @Override
    public List<Coupon> getCouponListByRestaurantId(long restaurantId) {

        List<Coupon> couponList = couponDAO.getCouponListByRestaurantId(restaurantId);

        return couponList;
    }

    @Override
    public CouponBean getCouponById(long couponId) {

        Coupon coupon = couponDAO.findById(Coupon.class, couponId);

        CouponBean couponBean = new CouponBean(coupon);

        return couponBean;
    }
}
