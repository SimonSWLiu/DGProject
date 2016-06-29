package com.onemenu.server.dao;

import java.util.List;

import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.model.OrderForm;



public interface OrderFormDAO extends BaseDAOInf {

    public List<OrderForm> getOrderFormListByRestaurantId(long customerId) throws RuntimeException;
    
    public OrderForm findById(long orderFormId) throws RuntimeException;
    
    public List<OrderForm> getOrderFormListByCondition(OrderFormQueryCondition condition);
    
    public List getPaymentPieByCondition(OrderFormQueryCondition condition);
    
    public List getAxeByCondition(OrderFormQueryCondition condition);
    
    public List getlineByCondition(OrderFormQueryCondition condition);
    
    public boolean updateOrderFormStatus(long orderFormId, String status) throws RuntimeException;

}
