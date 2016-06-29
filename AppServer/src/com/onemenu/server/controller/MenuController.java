package com.onemenu.server.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.model.Coupon;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.DishCategory;
import com.onemenu.server.model.Restaurant;

/**
 * 
 * @author lin
 *
 */
public interface MenuController extends AbstractController {

    /**
     * 显示增加菜式分类的dialog
     * 
     * @return
     */
    public Object addDishCategory(HttpSession session, DishCategory dishCategory);
    
    public Object editDishCategory(HttpSession session, DishCategory newDishCategory);
    
    public Object getDishCategoryById(HttpSession session, Long dishCategoryId);
    
    public Object getDishCategoryList(HttpSession session);

    public ModelAndView showMenuItemAddPage(HttpSession session);

    public ModelAndView showMenuItemEditPage(HttpSession session, Dish dish);

    public Boolean delMenuItem(HttpSession session, Dish dish);

    public Boolean disableMenuItem(HttpSession session, Dish dish);

    public Boolean enableMenuItem(HttpSession session, Dish dish);

    public ModelAndView showMenuListPage(HttpSession session);

    public ModelAndView showIndigrentListPage(HttpSession session);

    public ModelAndView showDishCategoryEditPage(HttpSession session);

    public ModelAndView showCouponListPage(HttpSession session);

    public ModelAndView addCouponItem(HttpSession session, Coupon coupon,
            Long couponTargetDishIds[], Long couponExtraCriDishIds[]) throws IOException;
    
    public ModelAndView editCouponItem(HttpSession session, Coupon coupon,
            Long couponTargetDishIds[], Long couponExtraCriDishIds[]) throws IOException;

    public Object getCouponById(HttpSession session, Long mId);
    
    public Object getDishBeanList(HttpSession session);
    
    public Boolean delCouponItem(HttpSession session, Long mId[]);

    public ModelAndView addMenuItem(HttpSession session, Dish dish, String base64Str, String width,
            String height) throws Exception;

    public ModelAndView eidtMenuItem(HttpSession session, Dish dish, String base64Str,
            String width, String height) throws Exception;

    public Object delDishCategory(HttpSession session, String dishCategoryListStr);

    public ModelAndView showStatementPage(HttpSession session);

    public Object queryStatement(HttpSession session, String fromDateTime, String toDateTime);
    
    public Object queryPaymentPie(HttpSession session, String fromDateTime, String toDateTime);
    
    public Object queryPaymentAxe(HttpSession session, String fromDateTime, String toDateTime);

    public ModelAndView showRestaurantProfilePage(HttpSession session);

    public ModelAndView editRestaurantDetails(HttpSession session, Restaurant curRestaurant,
            String base64Str, String width, String height) throws Exception;

    public ModelAndView showSettingsPage(HttpSession session);

    public Boolean disableRestaurant(HttpSession session, Restaurant restaurant);

    public Boolean enableRestaurant(HttpSession session, Restaurant restaurant);

    public ModelAndView showChangePasswordPage(HttpSession session);

    public ModelAndView changePassword(HttpSession session, String oriPassword, String curPassword);

}
