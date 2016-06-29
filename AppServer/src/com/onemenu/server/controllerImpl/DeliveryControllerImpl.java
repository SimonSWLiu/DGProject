package com.onemenu.server.controllerImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.controller.DeliveryController;
import com.onemenu.server.javabean.bean.OrderFormBean;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.javabean.ienum.SessionKey;
import com.onemenu.server.model.Account;
import com.onemenu.server.model.OrderForm;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.OrderFormService;
import com.onemenu.server.util.TimestampUtil;

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
@RequestMapping("/deliveryController")
public class DeliveryControllerImpl implements DeliveryController {

    private OrderFormService mOrderFormService;

    @Autowired
    public void setmOrderFormService(OrderFormService mOrderFormService) {
        this.mOrderFormService = mOrderFormService;
    }

    @Override
    @RequestMapping("/showCallDelivery")
    public ModelAndView showCallDelivery(HttpSession session) {

        ModelAndView model = new ModelAndView("restaurant/delivery/CallDelivery");

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
    @RequestMapping(value = "/callDeliveryForOrderForm", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean callDeliveryForOrderForm(HttpSession session, OrderForm orderForm, int minInterval) {

        boolean result = false;
        
        try {
            
            OrderForm orForm = mOrderFormService.findById(OrderForm.class, orderForm.getmId());
            orForm.setmCancelTimestamp(TimestampUtil.getMinIntervalTimestamp(minInterval));
            orForm.setmStatus(ParameterConstant.ORDER_FORM_STATUS_CALL_DELIVERY);
            mOrderFormService.updateTrd(orForm);
            
            result = true;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
       
        return result;
    }
    
    @Override
    @RequestMapping(value = "/cancelOrderFormByRestaurant", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean cancelOrderFormByRestaurant(HttpSession session, OrderForm orderForm) {

        return mOrderFormService.cancelOrderForm(orderForm.getmId());
    }

    @Override
    @RequestMapping(value = "/getPendingOrderFormList", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getPendingOrderFormList(HttpSession session) throws Exception {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();
        
        OrderFormQueryCondition condition = new OrderFormQueryCondition();
        condition.setRestaurantId(restaurant.getmId());
        condition.setStatus(ParameterConstant.ORDER_FORM_STATUS_RESTAURANT_PENDING);
        condition.setIsOneMenu(ParameterConstant.ORDER_FORM_IS_ONE_MENU);
        
        List<OrderFormBean> orderFormBeanList = mOrderFormService.getOrderFormListByCondition(condition);

        return orderFormBeanList;
    }
    
    @Override
    @RequestMapping(value = "/addThirdPartyOrderForm", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean addThirdPartyOrderForm(HttpSession session, OrderForm orderForm, int minInterval) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();
        
        boolean result = false;

        try {
            
            orderForm.setmRestaurant(restaurant);
            orderForm.setmStatus(ParameterConstant.ORDER_FORM_STATUS_CALL_DELIVERY);
            orderForm.setmCallDeliveryTimestamp(TimestampUtil.getMinIntervalTimestamp(minInterval));
            orderForm.setmCreateTimestamp(TimestampUtil.getCurrentTimestamp());
            orderForm.setmIsOneMenu(ParameterConstant.ORDER_FORM_IS_NOT_ONE_MENU);
            mOrderFormService.saveTrd(orderForm);
            result = true;
        } catch (Exception e) {
            
            e.printStackTrace();
        }

        return result;
    }
}
