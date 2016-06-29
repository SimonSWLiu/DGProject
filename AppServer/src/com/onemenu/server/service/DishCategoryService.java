package com.onemenu.server.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.onemenu.server.javabean.bean.DishCategoryBean;

/**
 * 
 * @author simonliu
 *
 */
public interface DishCategoryService extends AbstractServiceInf {

    public List<DishCategoryBean> getDishCategoryBeanList(HttpSession session);
    
}
