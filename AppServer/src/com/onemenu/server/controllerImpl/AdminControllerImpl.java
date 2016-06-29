package com.onemenu.server.controllerImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.controller.AdminController;
import com.onemenu.server.javabean.bean.DeliveryTimeSheetBean;
import com.onemenu.server.javabean.bean.DriverBean;
import com.onemenu.server.javabean.bean.OrderFormBean;
import com.onemenu.server.javabean.bean.RestaurantBean;
import com.onemenu.server.javabean.condition.DriverQueryCondition;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.javabean.condition.RestaurantQueryCondition;
import com.onemenu.server.service.DeliveryTimeSheetService;
import com.onemenu.server.service.DriverService;
import com.onemenu.server.service.OrderFormService;
import com.onemenu.server.service.RestaurantService;

@Controller
@RequestMapping("/adminController")
public class AdminControllerImpl implements AdminController {

    @Autowired
    private RestaurantService mRestaurantService;
    @Autowired
    private DriverService mDriverService;
    @Autowired
    private OrderFormService mOrderFormService;
    @Autowired
    private DeliveryTimeSheetService mDeliveryTimeSheetService;

    @RequestMapping("/showAdminMainPage")
    public ModelAndView showAdminMainPage() {
        return new ModelAndView("admin/Main");
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showRestaurantApprovalPage")
    public ModelAndView showRestaurantApprovalPage() {
        ModelAndView model = new ModelAndView("admin/RestaurantApproval");

        RestaurantQueryCondition condition = new RestaurantQueryCondition();
        condition.setStatus(ParameterConstant.RESTAURANT_STATUS_PENDING);
        List<RestaurantBean> restaurantBeanList =
                mRestaurantService.getRestaurantListByCondition(condition);
        model.addObject("restaurantBeanList", restaurantBeanList);

        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showDriverApprovalPage")
    public ModelAndView showDriverApprovalPage() {
        ModelAndView model = new ModelAndView("admin/DriverApproval");

        DriverQueryCondition conditon = new DriverQueryCondition();
        conditon.setStatus(ParameterConstant.DRIVER_STATUS_PENDING);
        List<DriverBean> driverBeanList = mDriverService.getDriverListByCondition(conditon);
        model.addObject("driverBeanList", driverBeanList);

        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showDeliveryTimeSheetPage")
    public ModelAndView showDeliveryTimeSheetPage() {
        ModelAndView model = new ModelAndView("admin/DeliveryTimeSheet");

        List<DeliveryTimeSheetBean> deliveryTimeSheetBeanList = mDeliveryTimeSheetService.getDeliveryTimeSheetBeanList();
        model.addObject("deliveryTimeSheetBeanList", deliveryTimeSheetBeanList);
        
        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showOneMenuConfigPage")
    public ModelAndView showOneMenuConfigPage() {
        ModelAndView model = new ModelAndView("restaurant/RestaurantApproval");

        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showRestaurantListPage")
    public ModelAndView showRestaurantListPage() {
        ModelAndView model = new ModelAndView("admin/RestaurantList");

        RestaurantQueryCondition conditon = new RestaurantQueryCondition();
        List<RestaurantBean> restaurantBeanList = mRestaurantService.getRestaurantListByCondition(conditon);
        model.addObject("restaurantBeanList", restaurantBeanList);

        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showDriverListPage")
    public ModelAndView showDriverListPage() {
        ModelAndView model = new ModelAndView("admin/DriverList");

        DriverQueryCondition conditon = new DriverQueryCondition();
        List<DriverBean> driverBeanList = mDriverService.getDriverListByCondition(conditon);
        model.addObject("driverBeanList", driverBeanList);

        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/showOrderFormListPage")
    public ModelAndView showOrderFormListPage() {

        ModelAndView model = new ModelAndView("admin/OrderFormList");

        OrderFormQueryCondition condition = new OrderFormQueryCondition();
        List<OrderFormBean> orderFormBeanList =
                mOrderFormService.getOrderFormListByCondition(condition);
        model.addObject("orderFormBeanList", orderFormBeanList);

        return model;
    }

}
