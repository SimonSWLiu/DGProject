package com.onemenu.server.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.dao.DishCategoryDAO;
import com.onemenu.server.javabean.bean.DishCategoryBean;
import com.onemenu.server.javabean.ienum.SessionKey;
import com.onemenu.server.model.Account;
import com.onemenu.server.model.DishCategory;
import com.onemenu.server.service.DishCategoryService;


@Service
public class DishCategoryServiceImpl extends AbstractServiceImpl implements DishCategoryService {

    private DishCategoryDAO mDishCategoryDAO;

    @Autowired
    public void setmDishCategoryDAO(DishCategoryDAO mDishCategoryDAO) {
        this.mDishCategoryDAO = mDishCategoryDAO;
    }
    
    @Override
    public List<DishCategoryBean> getDishCategoryBeanList(HttpSession session) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        List<DishCategory> dishCategoryList =
                mDishCategoryDAO.getDishCategoryListByRestaurantId(account.getmMerchant().getmRestaurant().getmId());

        List<DishCategoryBean> dishCategoryBeanList = new ArrayList<DishCategoryBean>();

        for (DishCategory dishCategory : dishCategoryList) {
            DishCategoryBean dishCategoryBean = new DishCategoryBean(dishCategory);
            dishCategoryBeanList.add(dishCategoryBean);
        }

        return dishCategoryBeanList;
    }

}
