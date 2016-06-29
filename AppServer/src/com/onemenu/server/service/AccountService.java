package com.onemenu.server.service;

import com.onemenu.server.model.Account;

/**
 * 
 * @author simonliu
 *
 */
public interface AccountService extends AbstractServiceInf {

    /**
     * 登录
     * 
     * @param account
     * @return
     */
    public Account login(Account account);

    public boolean validateEmail(String email);
    
    public Boolean changePassword(Account account, String oriPassword, String curPassword);

}
