package com.onemenu.server.service;

import java.util.HashMap;
import java.util.List;

import com.onemenu.server.javabean.bean.DeliveryTimeSheetBean;
import com.onemenu.server.model.DeliveryTimeSheet;



public interface DeliveryTimeSheetService extends AbstractServiceInf {

    public boolean addDeliveryTimeSheet(DeliveryTimeSheet deliveryTimeSheet);
    
    public HashMap<String, List<DeliveryTimeSheetBean>> getDeliveryTimeSheetHashMap();
    
    public List<DeliveryTimeSheetBean> getDeliveryTimeSheetBeanList();

}
