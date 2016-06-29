package com.onemenu.server.controllerImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.controller.OrderController;
import com.onemenu.server.javabean.bean.OrderFormBean;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.javabean.ienum.SessionKey;
import com.onemenu.server.model.Account;
import com.onemenu.server.model.OrderForm;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.OrderFormService;

/**
 * 
 * <br>
 * 类描述: <br>
 * 功能详细描述:
 * 
 * @author
 * @date [2012-12-5]
 */
@Controller
@RequestMapping("/orderController")
public class OrderControllerImpl implements OrderController {

    private OrderFormService mOrderFormService;

    @Autowired
    public void setmOrderFormService(OrderFormService mOrderFormService) {
        this.mOrderFormService = mOrderFormService;
    }

    @Override
    @RequestMapping("/showOrderFormList")
    public ModelAndView showOrderFormList(HttpSession session) {

        ModelAndView model = new ModelAndView("restaurant/order/OrderFormList");

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        OrderFormQueryCondition condition = new OrderFormQueryCondition();
        condition.setRestaurantId(restaurant.getmId());

        List orderFormList = mOrderFormService.listOrderFormByCondition(condition);

        model.addObject("orderFormList", orderFormList);

        return model;
    }

    @Override
    @RequestMapping(value = "/confirmOrderFormByRestaurant", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean confirmOrderFormByRestaurant(HttpSession session, OrderForm orderForm) {

        return mOrderFormService.confirmOrderFormByRestaurant(orderForm.getmId());
    }
    
    @Override
    @RequestMapping(value = "/cancelOrderForm", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean cancelOrderForm(HttpSession session, OrderForm orderForm) {

        return mOrderFormService.cancelOrderForm(orderForm.getmId());
    }

    @Override
    @RequestMapping(value = "/getOrderForm", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getOrderForm(HttpSession session, long orderFormId) throws Exception {

        OrderFormBean orderFormBean = mOrderFormService.getOrderFormByOrderFormId(orderFormId);

        return orderFormBean;
    }
}
