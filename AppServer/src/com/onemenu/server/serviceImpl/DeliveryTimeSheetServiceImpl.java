package com.onemenu.server.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onemenu.server.javabean.bean.DeliveryTimeSheetBean;
import com.onemenu.server.model.DeliveryTimeSheet;
import com.onemenu.server.service.DeliveryTimeSheetService;


@Service
public class DeliveryTimeSheetServiceImpl extends AbstractServiceImpl implements
        DeliveryTimeSheetService {

    private static final Logger logger = Logger.getLogger(DeliveryTimeSheetServiceImpl.class);

    @Override
    @Transactional
    public boolean addDeliveryTimeSheet(DeliveryTimeSheet deliveryTimeSheet) {

        boolean result = false;

        try {

            saveTrd(deliveryTimeSheet);
            result = true;
        } catch (Exception e) {

        }


        return result;
    }

    @Override
    public HashMap<String, List<DeliveryTimeSheetBean>> getDeliveryTimeSheetHashMap() {

        HashMap<String, List<DeliveryTimeSheetBean>> deliveryTimeSheetHashMap =
                new HashMap<String, List<DeliveryTimeSheetBean>>();

        List<DeliveryTimeSheet> deliveryTimeSheetList = findAll(DeliveryTimeSheet.class);

        for (DeliveryTimeSheet deliveryTimeSheet : deliveryTimeSheetList) {

            String key = deliveryTimeSheet.getmAvailableDate();

            DeliveryTimeSheetBean deliveryTimeSheetBean =
                    new DeliveryTimeSheetBean(deliveryTimeSheet);

            if (deliveryTimeSheetHashMap.containsKey(key)) {

                List<DeliveryTimeSheetBean> list = deliveryTimeSheetHashMap.get(key);
                list.add(deliveryTimeSheetBean);
                deliveryTimeSheetHashMap.put(key, list);
            } else {

                List<DeliveryTimeSheetBean> list = new ArrayList<DeliveryTimeSheetBean>();
                list.add(deliveryTimeSheetBean);
                deliveryTimeSheetHashMap.put(key, list);
            }
        }

        return deliveryTimeSheetHashMap;
    }

    @Override
    public List<DeliveryTimeSheetBean> getDeliveryTimeSheetBeanList() {

        List<DeliveryTimeSheet> deliveryTimeSheetList = findAll(DeliveryTimeSheet.class);

        List<DeliveryTimeSheetBean> deliveryTimeSheetBeanList = new ArrayList<DeliveryTimeSheetBean>();

        for (DeliveryTimeSheet deliveryTimeSheet : deliveryTimeSheetList) {

            DeliveryTimeSheetBean deliveryTimeSheetBean =
                    new DeliveryTimeSheetBean(deliveryTimeSheet);

            deliveryTimeSheetBeanList.add(deliveryTimeSheetBean);
        }

        return deliveryTimeSheetBeanList;
    }
}
