package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.model.DeliveryTimeSheet;

public interface DeliveryTimeSheetAdminController extends AbstractController {

    public ModelAndView addDeliveryTimeSheet(HttpSession session,
            DeliveryTimeSheet deliveryTimeSheet);

    public ModelAndView editDeliveryTimeSheet(HttpSession session,
            DeliveryTimeSheet deliveryTimeSheet);

    public Object getDeliveryTimeSheetById(HttpSession session, Long mId);
}
