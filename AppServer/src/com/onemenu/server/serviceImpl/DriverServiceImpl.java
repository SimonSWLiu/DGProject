package com.onemenu.server.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.dao.DriverDAO;
import com.onemenu.server.javabean.bean.DriverBean;
import com.onemenu.server.javabean.condition.DriverQueryCondition;
import com.onemenu.server.model.Driver;
import com.onemenu.server.service.DriverService;

@Service
public class DriverServiceImpl extends AbstractServiceImpl implements DriverService {

    private static final Logger logger = Logger.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverDAO mDriverDAO;

    @Override
    public List<DriverBean> getDriverListByCondition(DriverQueryCondition condition) {

        List<Driver> driverList = mDriverDAO.getDriverListByCondition(condition);

        List<DriverBean> driverBeanList = new ArrayList<DriverBean>();

        for (Driver driver : driverList) {
            DriverBean driverBean = new DriverBean(driver);
            driverBeanList.add(driverBean);
        }

        return driverBeanList;
    }

    @Override
    @Transactional
    public boolean approveDriverById(long driverId) {

        return mDriverDAO.updateDriverStatus(driverId,
                ParameterConstant.DRIVER_STATUS_UNAVAILIABLE);
    }

    @Override
    @Transactional
    public boolean rejectDriverById(long driverId) {

        return mDriverDAO.updateDriverStatus(driverId,
                ParameterConstant.DRIVER_STATUS_RESIGNED);
    }
    
    @Override
    public DriverBean getDriverBeanById(long driverId){
        
        Driver driver = mDriverDAO.findById(Driver.class, driverId);
        DriverBean driverBean = new DriverBean(driver);
        
        return driverBean;
    }

}
