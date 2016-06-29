package com.onemenu.server.serviceImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.model.Account;
import com.onemenu.server.service.AccountService;

@Service
public class AccountServiceImpl extends AbstractServiceImpl implements AccountService {


    @Override
    public Account login(Account account) {

        DetachedCriteria accountCri = DetachedCriteria.forClass(Account.class);

        accountCri.add(Restrictions.eq("mEmail", account.getmEmail()));
        accountCri.add(Restrictions.eq("mPassword", account.getmPassword()));

        List<?> list = getBaseDAO().findByCriteria(accountCri, Account.class);

        if (list.size() != 0) {
            return (Account) list.get(0);
        } else if (list.size() == 0) {
            return null;
        }
        // else {
        // throw new Exception("存在一个以上的用户, 登录名和密码相同");
        // }
        return null;
    }

    // Return true if Email is not exist.
    public boolean validateEmail(String email) {

        long count = findRowCount(Account.class, "mEmail", email);
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isSignUp(String userId) {
        long count = findRowCount(Account.class, "mThirdPartyId", userId);
        return count != 0;
    }

    @Override
    @Transactional
    public Boolean changePassword(Account account, String oriPassword, String curPassword) {

        Boolean result = false;

        account.setmPassword(oriPassword);
        Account login = login(account);
        if (login != null) {
            
            login.setmPassword(curPassword);
            updateTrd(login);

            result = true;
        }
        
        return result;
    }
}
