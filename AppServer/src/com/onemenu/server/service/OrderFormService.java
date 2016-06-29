package com.onemenu.server.service;

import java.util.List;

import com.onemenu.server.javabean.bean.OrderFormBean;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.model.OrderForm;

/**
 * 
 * @author simonliu
 *
 */
public interface OrderFormService extends AbstractServiceInf {

    public List<OrderForm> listOrderFormByRestaurantId(long restaurantId);

    public boolean confirmOrderFormByRestaurant(long orderFromId);
    
    public boolean finishOrderForm(long orderFromId);
    
    public boolean cancelOrderForm(long orderFromId);
    
    public OrderFormBean getOrderFormByOrderFormId(long orderFormId);
    
    public List<OrderFormBean> getOrderFormListByCondition(OrderFormQueryCondition condition);
    
    public List listStatementByCondition(OrderFormQueryCondition condition);

    public List listPaymentPieByCondition(OrderFormQueryCondition condition);
    
    public List listAxeByCondition(OrderFormQueryCondition condition);
    
    public List listLineByCondition(OrderFormQueryCondition condition);
    
    public List<OrderForm> listOrderFormByCondition(OrderFormQueryCondition condition);
}
