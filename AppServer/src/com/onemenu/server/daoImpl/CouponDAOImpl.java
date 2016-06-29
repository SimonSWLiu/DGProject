package com.onemenu.server.daoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.dao.CouponDAO;
import com.onemenu.server.model.Coupon;
import com.onemenu.server.model.Restaurant;

@Repository
public class CouponDAOImpl extends BaseDAOSupport implements CouponDAO {

    protected HibernateTemplate mHibernateTemplate;

    @Autowired
    public void setmHibernateTemplate(HibernateTemplate mHibernateTemplate) {
        this.mHibernateTemplate = mHibernateTemplate;
    }
    @Override
    @Transactional
    public List<Coupon> getCouponListByRestaurantId(long restaurantId) {

        List<Coupon> couponList = new ArrayList<Coupon>();

        Restaurant restaurant = mHibernateTemplate.get(Restaurant.class, restaurantId);

        if (restaurant != null) {
            
            Set<Coupon> couponSet = restaurant.getmCouponSet();
            
            for (Coupon coupon : couponSet) {

                couponList.add(coupon);
            }
        }

        Collections.sort(couponList, new Comparator<Coupon>() {

            public int compare(Coupon arg0, Coupon arg1) {
                return arg1.getmCreateTimestamp().compareTo(arg0.getmCreateTimestamp());
            }
        });
        return couponList;
    }

}
