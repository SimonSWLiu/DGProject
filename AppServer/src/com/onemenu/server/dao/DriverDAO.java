package com.onemenu.server.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.onemenu.server.javabean.condition.DriverQueryCondition;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.model.Driver;
import com.onemenu.server.model.OrderForm;

public interface DriverDAO extends BaseDAOInf {

    public List<Driver> getDriverListByCondition(DriverQueryCondition condition);
    
    public boolean updateDriverStatus(long driverId, Integer status) throws RuntimeException;

}
