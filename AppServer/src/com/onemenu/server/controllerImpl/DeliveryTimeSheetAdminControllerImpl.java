package com.onemenu.server.controllerImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.controller.DeliveryTimeSheetAdminController;
import com.onemenu.server.javabean.bean.DeliveryTimeSheetBean;
import com.onemenu.server.javabean.bean.DishBean;
import com.onemenu.server.model.DeliveryTimeSheet;
import com.onemenu.server.service.DeliveryTimeSheetService;

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
@RequestMapping("/deliveryTimeSheetAdminController")
public class DeliveryTimeSheetAdminControllerImpl implements DeliveryTimeSheetAdminController {

    @Autowired
    private DeliveryTimeSheetService mDeliveryTimeSheetService;

    @Override
    @RequestMapping(value = "/addDeliveryTimeSheet", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    public ModelAndView addDeliveryTimeSheet(HttpSession session,
            DeliveryTimeSheet deliveryTimeSheet) {

        mDeliveryTimeSheetService.saveTrd(deliveryTimeSheet);

        return new ModelAndView("redirect:../adminController/showDeliveryTimeSheetPage");
    }

    @Override
    @RequestMapping(value = "/editDeliveryTimeSheet", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    public ModelAndView editDeliveryTimeSheet(HttpSession session,
            DeliveryTimeSheet deliveryTimeSheet) {

        DeliveryTimeSheet oriDeliveryTimeSheet =
                mDeliveryTimeSheetService.findById(DeliveryTimeSheet.class,
                        deliveryTimeSheet.getmId());
        oriDeliveryTimeSheet.setmAvailableDate(deliveryTimeSheet.getmAvailableDate());
        oriDeliveryTimeSheet.setmAvailableStartTime(deliveryTimeSheet.getmAvailableStartTime());
        oriDeliveryTimeSheet.setmAvailableEndTime(deliveryTimeSheet.getmAvailableEndTime());
        oriDeliveryTimeSheet.setmQuantity(deliveryTimeSheet.getmQuantity());
        mDeliveryTimeSheetService.updateTrd(oriDeliveryTimeSheet);

        return new ModelAndView("redirect:../adminController/showDeliveryTimeSheetPage");
    }

    @Override
    @RequestMapping(value = "/getDeliveryTimeSheetById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getDeliveryTimeSheetById(HttpSession session, Long mId) {

        DeliveryTimeSheet deliveryTimeSheet =
                mDeliveryTimeSheetService.findById(DeliveryTimeSheet.class, mId);
        DeliveryTimeSheetBean deliveryTimeSheetBean = new DeliveryTimeSheetBean(deliveryTimeSheet);

        return deliveryTimeSheetBean;
    }

}
