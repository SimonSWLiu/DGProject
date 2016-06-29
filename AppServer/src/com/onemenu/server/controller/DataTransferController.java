package com.onemenu.server.controller;

import javax.servlet.http.HttpSession;



public interface DataTransferController extends AbstractController {

    public Object transDishToIngredient(HttpSession session);
    
    public Object transDishImage(HttpSession session);

}
