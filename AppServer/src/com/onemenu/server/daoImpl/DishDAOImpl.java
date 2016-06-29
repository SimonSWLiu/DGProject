package com.onemenu.server.daoImpl;

import org.hibernate.Hibernate;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.dao.DishDAO;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.DishOptionItem;
import com.onemenu.server.model.DishOptionType;

@Repository
public class DishDAOImpl extends BaseDAOSupport implements DishDAO {
    protected HibernateTemplate mHibernateTemplate;

    @Autowired
    public void setmHibernateTemplate(HibernateTemplate mHibernateTemplate) {
        this.mHibernateTemplate = mHibernateTemplate;
    }

    @Override
    @Transactional
    public boolean updateDishStatus(long dishId, String status) {

        boolean result = false;

        Dish dish = mHibernateTemplate.get(Dish.class, dishId);
        dish.setmStatus(status);
        mHibernateTemplate.update(dish);

        result = true;

        return result;
    }

    @Override
    @Transactional
    public Dish getDishById(long dishId) {

        Dish dish = mHibernateTemplate.get(Dish.class, dishId);

        JSONObject jo = new JSONObject();
        try {
            jo.put("customization", dish.getmDishOptionTypeSet());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(dish.getmId());
        System.out.println(jo.toString());
        
        Hibernate.initialize(dish.getmDishOptionTypeSet());  
        for (DishOptionType dishOptionType : dish.getmDishOptionTypeSet()) {

            Hibernate.initialize(dishOptionType.getmDishOptionItemSet());  
        }
        
        return dish;
    }

}
