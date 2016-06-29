package com.onemenu.server.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


public interface CommonController extends AbstractController {

    public ModelAndView showMainContentPage();

    public ModelAndView showPendingApprovalMsgPage();

    public Object validateField(String fieldId, String fieldValue, String _);

    public String generateBase64String(MultipartFile file) throws Exception;

}
