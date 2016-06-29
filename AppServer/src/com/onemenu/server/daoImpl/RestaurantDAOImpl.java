package com.onemenu.server.daoImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onemenu.server.dao.RestaurantDAO;
import com.onemenu.server.javabean.condition.RestaurantQueryCondition;
import com.onemenu.server.model.Restaurant;

@Repository
public class RestaurantDAOImpl extends BaseDAOSupport implements RestaurantDAO {

    @Autowired
    protected HibernateTemplate mHibernateTemplate;

    @Override
    @Transactional
    public List<Restaurant> getRestaurantListByCondition(RestaurantQueryCondition condition) {

        DetachedCriteria restCri = DetachedCriteria.forClass(Restaurant.class);
        restCri.addOrder(Order.asc("mCreateTimestamp"));

        if (condition.getRestaurantId() != null) {
            restCri.add(Restrictions.eq("mId", condition.getRestaurantId()));
        }

        if (condition.getStatus() != null) {
            restCri.add(Restrictions.eq("mStatus", condition.getStatus()));
        }

        List<Restaurant> restList = mHibernateTemplate.findByCriteria(restCri);

        return restList;
    }
    
    @Override
    @Transactional
    public boolean updateRestaurantStatus(long restId, Integer status) {

        boolean result = false;

        Restaurant restaurant = mHibernateTemplate.get(Restaurant.class, restId);

        if (restaurant != null) {

            restaurant.setmStatus(status);
            mHibernateTemplate.update(restaurant);

            result = true;
        }

        return result;
    }

}
