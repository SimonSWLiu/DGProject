package com.onemenu.server.controllerImpl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onemenu.server.controller.DriverAdminController;
import com.onemenu.server.javabean.bean.DriverBean;
import com.onemenu.server.model.Driver;
import com.onemenu.server.service.DriverService;

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
@RequestMapping("/driverAdminController")
public class DriverAdminControllerImpl implements DriverAdminController {
    
    @Autowired
    private DriverService mDriverService;

    @Override
    @RequestMapping(value = "/approveDriverById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean approveDriverById(HttpSession session, Driver driver) {

        return mDriverService.approveDriverById(driver.getmId());
    }
    
    @Override
    @RequestMapping(value = "/rejectDriverById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean rejectDriverById(HttpSession session, Driver driver) {

        return mDriverService.rejectDriverById(driver.getmId());
    }

    @Override
    @RequestMapping(value = "/getDriverById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getDriverById(HttpSession session, long driverId) throws Exception {

        DriverBean driverBean = mDriverService.getDriverBeanById(driverId);

        return driverBean;
    }
}
