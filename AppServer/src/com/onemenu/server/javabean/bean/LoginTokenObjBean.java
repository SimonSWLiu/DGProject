package com.onemenu.server.javabean.bean;

import java.sql.Timestamp;

import com.onemenu.server.model.Account;

/**
 * 
 * @author simonliu
 *
 */
public class LoginTokenObjBean {

    public String accessToken;
    public String accessTokenContent;
    public Timestamp loginTimestamp;
    public Account account;
}
