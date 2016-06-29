package com.onemenu.server.daoImpl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onemenu.server.dao.DishCategoryDAO;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.DishCategory;
import com.onemenu.server.model.DishOptionType;

@Repository
public class DishCategoryDAOImpl extends BaseDAOSupport implements DishCategoryDAO {
    protected HibernateTemplate mHibernateTemplate;

    @Autowired
    public void setmHibernateTemplate(HibernateTemplate mHibernateTemplate) {
        this.mHibernateTemplate = mHibernateTemplate;
    }

    @Override
    @Transactional
    public List<DishCategory> getDishCategoryListByRestaurantId(long restaurantId) {

        DetachedCriteria dishCategoryCri = DetachedCriteria.forClass(DishCategory.class);
        dishCategoryCri.setFetchMode("mDishSet", FetchMode.SELECT);
        dishCategoryCri.add(Restrictions.eq("mRestaurant.mId", restaurantId));
        dishCategoryCri.addOrder(Order.asc("mSequence"));

        List<DishCategory> dishCategorieList = mHibernateTemplate.findByCriteria(dishCategoryCri);

        for (DishCategory dishCategory : dishCategorieList) {

            for (Dish dish : dishCategory.getmDishSet()) {
                
                Hibernate.initialize(dish.getmDishOptionTypeSet());
                for (DishOptionType dishOptionType : dish.getmDishOptionTypeSet()) {

                    Hibernate.initialize(dishOptionType.getmDishOptionItemSet());
                }
            }

        }


        return dishCategorieList;
    }

}
