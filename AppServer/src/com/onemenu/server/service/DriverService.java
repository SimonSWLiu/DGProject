package com.onemenu.server.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.onemenu.server.javabean.bean.DriverBean;
import com.onemenu.server.javabean.condition.DriverQueryCondition;



public interface DriverService extends AbstractServiceInf {
	
    public List<DriverBean> getDriverListByCondition(DriverQueryCondition condition);
    
    public boolean approveDriverById(long driverId);
    
    public boolean rejectDriverById(long driverId);
    
    public DriverBean getDriverBeanById(long driverId);
}
