package com.onemenu.server.exceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.javabean.ienum.SessionKey;
import com.onemenu.server.model.Account;

/**
 * 
 * <br>
 * 类描述: <br>
 * 功能详细描述:
 * 
 * @author linhang
 * @date [2012-11-14]
 */
public class ControllerExceptionHandler implements HandlerExceptionResolver {
    private Logger mLogger = Logger.getLogger(ControllerExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
            Object object, Exception exception) {
        ModelAndView model = new ModelAndView("/error/Error");

        Account account =
                (Account) request.getSession().getAttribute(SessionKey.LoginBean.toString());
        if (account != null) {
            mLogger.error("登录的用户名为:" + account.getmEmail());
        }

        mLogger.error("异常信息:", exception);
        model.addObject("exception", exception);
        return model;
    }

}
