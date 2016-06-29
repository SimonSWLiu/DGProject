package com.onemenu.server.controllerImpl;

import java.io.File;

import java.io.InputStream;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.controller.LoginController;
import com.onemenu.server.javabean.bean.BaseResultBean;
import com.onemenu.server.javabean.ienum.SessionKey;
import com.onemenu.server.model.Account;
import com.onemenu.server.model.Merchant;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.AccountService;
import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.ImageUtils;
import com.onemenu.server.util.MD5Utils;
import com.onemenu.server.util.MimeUtils;
import com.onemenu.server.util.TimestampUtil;

/**
 * 
 * <br>
 * 类描述: <br>
 * 功能详细描述:
 * 
 * @author
 * @date [2012-12-5]
 */
@Controller
@RequestMapping("/loginController")
public class LoginControllerImpl implements LoginController {

    private AccountService mAccountService;

    @Autowired
    public void setmAccountService(AccountService mAccountService) {
        this.mAccountService = mAccountService;
    }

    @Override
    @RequestMapping("/showLoginPage")
    public ModelAndView showLoginPage(HttpSession session) {

        ModelAndView model = new ModelAndView("Login");
        return model;
    }

    @Override
    @RequestMapping("/showAdminLoginPage")
    public ModelAndView showAdminLoginPage(HttpSession session) {

        ModelAndView model = new ModelAndView("AdminLogin");
        return model;
    }

    @Override
    @RequestMapping(value = "/validateLogin", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object validateLogin(HttpSession session) {

        BaseResultBean result = new BaseResultBean();
        result.setSuccess(false);

        result.setSuccess(true);
        result.setMsg("Your restaurant is pending approval...");

        return result;
    }

    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, Account account) throws Exception {

        ModelAndView model = new ModelAndView();
        Account login = mAccountService.login(account);

        if (login != null) {

            Restaurant restaurant = login.getmMerchant().getmRestaurant();

            if (restaurant != null) {

                if (restaurant.getmStatus().equals(ParameterConstant.RESTAURANT_STATUS_PENDING)) {
                    model.addObject("message", "Your restaurant is pending approval...");
                    model.setViewName("Login");
                } else {

                    model.setViewName("redirect:../commonController/showMainPage");
                    session.setAttribute(SessionKey.LoginBean.toString(), login);
                }

            } else {

                model.setViewName("redirect:../loginController/showRestaurantRegistrationPage");
                session.setAttribute(SessionKey.LoginBean.toString(), login);
            }

        } else {
            model.addObject("message", "Account or passwrod error.");
            model.setViewName("Login");
        }

        return model;
    }

    @Override
    @RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
    public ModelAndView adminLogin(HttpSession session, Account account) throws Exception {

        ModelAndView model = new ModelAndView();
        Account login = mAccountService.login(account);

        if (login != null) {

            if (!login.getmStatus().equals("")) {
                if (login.getmStatus().equals(ParameterConstant.ACCOUNT_STATUS_ADMIN)) {
                    model.setViewName("redirect:../adminController/showAdminMainPage");
                    session.setAttribute(SessionKey.LoginBean.toString(), login);
                } else {

                    model.addObject("message", "Yours is not admin account.");
                    model.setViewName("AdminLogin");
                }
            } else {

                model.addObject("message", "Yours is not admin account.");
                model.setViewName("AdminLogin");
            }
        } else {

            model.addObject("message", "Account or passwrod error.");
            model.setViewName("AdminLogin");
        }

        return model;
    }

    @Override
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        ModelAndView model = new ModelAndView("Login");
        session.setAttribute(SessionKey.LoginBean.toString(), null);
        session.invalidate();

        return model;
    }

    /**
     * 跳转菜单列表页面
     */
    @Override
    @RequestMapping("/showMerchantRegistrationPage")
    public ModelAndView showMerchantRegistrationPage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/merchant/MerchantRegistration");
        return model;
    }

    /**
     * 跳转菜单列表页面
     */
    @Override
    @RequestMapping("/showRestaurantRegistrationPage")
    public ModelAndView showRestaurantRegistrationPage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/RestaurantProfile");
        return model;
    }

    /**
     * 跳转饭馆审核页面
     */
    @Override
    @RequestMapping("/merchantRegister")
    public ModelAndView merchantRegister(HttpSession session, Account account) {

        ModelAndView model = new ModelAndView();

        Timestamp curTimestamp = TimestampUtil.getCurrentTimestamp();

        if (mAccountService.validateEmail(account.getmEmail())) {

            Merchant merchant = account.getmMerchant();
            if (merchant != null) {

                account.setmCreateTimestamp(curTimestamp);
                merchant.setmCreateTimestamp(curTimestamp);
                account.setmMerchant(merchant);
                mAccountService.saveTrd(account);
            }

            Account login = mAccountService.login(account);

            model.setViewName("redirect:./showRestaurantRegistrationPage");
            session.setAttribute(SessionKey.LoginBean.toString(), login);

        } else {
            model.addObject("message", "Email exists.");

        }

        return model;
    }


    @Override
    @RequestMapping(value = "/registerRestaurant", method = RequestMethod.POST)
    public ModelAndView registerRestaurant(HttpSession session, Restaurant restaurant,
            String base64Str, String width, String height) throws Exception {

        ModelAndView model = new ModelAndView();

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Merchant merchant = account.getmMerchant();

        restaurant.setmLogo("");
        restaurant.setmLogoWidth(0);
        restaurant.setmLogoHeight(0);
        restaurant.setmLogoUrl("");
        restaurant.setmStatus(ParameterConstant.RESTAURANT_STATUS_PENDING);
        restaurant.setTopTagsName("");
        restaurant.setmMerchant(merchant);
        restaurant.setmCreateTimestamp(TimestampUtil.getCurrentTimestamp());

        // generate a file name
        String folderName =
                MD5Utils.getHashCodePrefix(String.valueOf(System.currentTimeMillis()))
                        + System.currentTimeMillis();
        restaurant.setmFolderName(folderName);

        if (!base64Str.equals("")) {

            // Generate image name
            String mimetype = AwsS3Utils.getMineType(base64Str);
            String extension = MimeUtils.guessExtensionFromMimeType(mimetype);
            String imageName = AwsS3Utils.generateImageName(width, height, extension);

            // upload image to s3
            InputStream in = ImageUtils.getInputStreamFromBase64(base64Str);
            ObjectMetadata metaddata = new ObjectMetadata();
            metaddata.setContentType(mimetype);
            metaddata.setContentLength(in.available());
            AwsS3Utils.uploadFileToAwsS3RestaurantRes(folderName, imageName, in, metaddata);

            restaurant.setmLogo(imageName);
            restaurant.setmLogoWidth(Integer.parseInt(width));
            restaurant.setmLogoHeight(Integer.parseInt(height));
            restaurant.setmLogoUrl(AwsS3Utils.getAwsS3RestaurantResUploadPath() + folderName
                    + File.separator + imageName);
        }

        account.getmMerchant().setmRestaurant(restaurant);

        mAccountService.updateTrd(account);

        // 跳转到审核页面
        model.setViewName("redirect:../commonController/showPendingApprovalMsgPage");

        return model;

    }

}
