package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;

import com.onemenu.server.model.Driver;

public interface DriverAdminController extends AbstractController {

    public boolean approveDriverById(HttpSession session, Driver driver);
    
    public boolean rejectDriverById(HttpSession session, Driver driver);
    
    public Object getDriverById(HttpSession session, long orderFromId) throws Exception;

}
