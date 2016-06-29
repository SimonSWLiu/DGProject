package com.onemenu.server.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.dao.DishCategoryDAO;
import com.onemenu.server.dao.DishDAO;
import com.onemenu.server.javabean.bean.DishBean;
import com.onemenu.server.javabean.bean.MenuItemDetailBean;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.DishCategory;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.DishService;

@Service
public class DishServiceImpl extends AbstractServiceImpl implements DishService {

    private DishDAO mDishDAO;
    private DishCategoryDAO mDishCategoryDAO;

    @Autowired
    public void setDishDAO(DishDAO mDishDAO) {
        this.mDishDAO = mDishDAO;
    }

    @Autowired
    public void setmDishCategoryDAO(DishCategoryDAO mDishCategoryDAO) {
        this.mDishCategoryDAO = mDishCategoryDAO;
    }

    public Map<String, List<MenuItemDetailBean>> getMenuItemMap(Restaurant restaurant) {

        Map<String, List<MenuItemDetailBean>> resultMap =
                new HashMap<String, List<MenuItemDetailBean>>();

        List<DishCategory> dishCategorieList =
                mDishCategoryDAO.getDishCategoryListByRestaurantId(restaurant.getmId());

        if (dishCategorieList.size() != 0) {

            for (DishCategory dishCategory : dishCategorieList) {

                String dishCategoryName = dishCategory.getmName();

                if (resultMap.containsKey(dishCategoryName)) {

                    List<MenuItemDetailBean> menuItemDetailBeanListByCategory =
                            new ArrayList<MenuItemDetailBean>();
                    for (Dish dish : dishCategory.getmDishSet()) {

                        if (dish.getmStatus().equals(ParameterConstant.DISH_STATUS_ENABLE)) {

                            MenuItemDetailBean menuItemDetailBean = new MenuItemDetailBean();
                            menuItemDetailBean.setMenuItemDetailBean(dish);
                            menuItemDetailBeanListByCategory.add(menuItemDetailBean);
                        }
                    }
                    if (menuItemDetailBeanListByCategory.size() != 0) {
                        resultMap.remove(dishCategoryName);
                        resultMap.put(dishCategoryName, menuItemDetailBeanListByCategory);
                    }
                } else {

                    List<MenuItemDetailBean> menuItemDetailBeanListByCategory =
                            new ArrayList<MenuItemDetailBean>();
                    for (Dish dish : dishCategory.getmDishSet()) {
                        MenuItemDetailBean menuItemDetailBean = new MenuItemDetailBean();
                        menuItemDetailBean.setMenuItemDetailBean(dish);
                        menuItemDetailBeanListByCategory.add(menuItemDetailBean);
                    }
                    if (menuItemDetailBeanListByCategory.size() != 0) {
                        resultMap.put(dishCategoryName, menuItemDetailBeanListByCategory);
                    }
                }
            }
        }

        return resultMap;
    }

    @Override
    public boolean disableDish(long dishId) {

        return mDishDAO.updateDishStatus(dishId, ParameterConstant.DISH_STATUS_DISABLE);
    }

    @Override
    public boolean enableDish(long dishId) {

        return mDishDAO.updateDishStatus(dishId, ParameterConstant.DISH_STATUS_ENABLE);
    }
    
    @Override
    public List<DishBean> getDishBeanList(Restaurant restaurant) {

        List<DishBean> dishBeanList = new ArrayList<DishBean>();

        List<DishCategory> dishCategorieList =
                mDishCategoryDAO.getDishCategoryListByRestaurantId(restaurant.getmId());

        if (dishCategorieList.size() != 0) {

            for (DishCategory dishCategory : dishCategorieList) {

                for (Dish dish : dishCategory.getmDishSet()) {

                    DishBean dishBean = new DishBean(dish);
                    dishBeanList.add(dishBean);
                }
            }
        }

        return dishBeanList;
    }
    
    @Override
    public Dish getDishById(long dishId) {

        return mDishDAO.getDishById(dishId);
    }

}
