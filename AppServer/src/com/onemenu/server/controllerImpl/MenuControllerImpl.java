package com.onemenu.server.controllerImpl;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.apache.http.util.TextUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.controller.MenuController;
import com.onemenu.server.javabean.bean.CouponBean;
import com.onemenu.server.javabean.bean.DishBean;
import com.onemenu.server.javabean.bean.DishCategoryBean;
import com.onemenu.server.javabean.bean.MenuItemDetailBean;
import com.onemenu.server.javabean.bean.BaseResultBean;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.javabean.ienum.SessionKey;
import com.onemenu.server.model.Account;
import com.onemenu.server.model.Coupon;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.DishCategory;
import com.onemenu.server.model.DishOptionItem;
import com.onemenu.server.model.DishOptionType;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.AccountService;
import com.onemenu.server.service.CouponService;
import com.onemenu.server.service.DishCategoryService;
import com.onemenu.server.service.DishService;
import com.onemenu.server.service.OrderFormService;
import com.onemenu.server.service.RestaurantService;
import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.ImageUtils;
import com.onemenu.server.util.MimeUtils;
import com.onemenu.server.util.TimestampUtil;
import com.onemenu.server.util.TimestampUtil.DateFormatType;

@Controller
@RequestMapping("/menuController")
public class MenuControllerImpl implements MenuController {
    
    @Autowired
    private DishCategoryService mDishCategoryService;
    @Autowired
    private DishService mDishService;
    @Autowired
    private CouponService mCouponService;
    @Autowired
    private OrderFormService mOrderFormService;
    @Autowired
    private AccountService mAccountService;
    @Autowired
    private RestaurantService mRestaurantService;

    @Override
    @RequestMapping(value = "/addDishCategory", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object addDishCategory(HttpSession session, DishCategory dishCategory) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        dishCategory.setmRestaurant(account.getmMerchant().getmRestaurant());
        dishCategory.setmCreateTimestamp(TimestampUtil.getCurrentTimestamp());
        mDishCategoryService.saveTrd(dishCategory);

        return getDishCategoryListJasonStr(session);
    }

    @Override
    @RequestMapping(value = "/editDishCategory", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object editDishCategory(HttpSession session, DishCategory newDishCategory) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());

        DishCategory dishCategory =
                mDishCategoryService.findById(DishCategory.class, newDishCategory.getmId());
        dishCategory.setmName(newDishCategory.getmName());
        dishCategory.setmSequence(newDishCategory.getmSequence());
        mDishCategoryService.updateTrd(dishCategory);

        return getDishCategoryListJasonStr(session);
    }

    @Override
    @RequestMapping(value = "/getDishCategoryById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getDishCategoryById(HttpSession session, Long dishCategoryId) {

        DishCategory dishCategory =
                mDishCategoryService.findById(DishCategory.class, dishCategoryId);

        DishCategoryBean dishCategoryBean = new DishCategoryBean(dishCategory);

        return dishCategoryBean;
    }

    @Override
    @RequestMapping(value = "/getDishCategoryList", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getDishCategoryList(HttpSession session) {

        return getDishCategoryListJasonStr(session);
    }

    private Object getDishCategoryListJasonStr(HttpSession session) {

        ObjectMapper mapper = new ObjectMapper();
        String dishCategoryListJsonStr = null;
        List<DishCategoryBean> dishCategoryBeanList =
                (List<DishCategoryBean>) mDishCategoryService.getDishCategoryBeanList(session);

        try {
            dishCategoryListJsonStr = mapper.writeValueAsString(dishCategoryBeanList);
        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dishCategoryListJsonStr;
    }

    @Override
    @RequestMapping("/showMenuItemAddPage")
    public ModelAndView showMenuItemAddPage(HttpSession session) {

        ModelAndView model = new ModelAndView("restaurant/menu/MenuItemEdit");

        List<DishCategoryBean> dishCategoryBeanList =
                mDishCategoryService.getDishCategoryBeanList(session);
        model.addObject("dishCategoryList", dishCategoryBeanList);

        return model;
    }

    @Override
    @RequestMapping("/showMenuItemEditPage")
    public ModelAndView showMenuItemEditPage(HttpSession session, Dish dish) {

        ModelAndView model = new ModelAndView("restaurant/menu/MenuItemEdit");

        Dish menuItem = mDishService.findById(Dish.class, dish.getmId());
        Dish test = mDishService.getDishById(dish.getmId());
        if (menuItem != null) {

            List<DishCategoryBean> dishCategoryBeanList =
                    mDishCategoryService.getDishCategoryBeanList(session);
            model.addObject("dishCategoryList", dishCategoryBeanList);

            MenuItemDetailBean menuItemDetailBean = new MenuItemDetailBean();
            menuItemDetailBean.setMenuItemDetailBean(menuItem);
            model.addObject("menuItem", menuItemDetailBean);

        }

        for (DishOptionType dishOptionType : test.getmDishOptionTypeSet()) {

            System.out.println("Option Type : " + dishOptionType.getmName());

            for (DishOptionItem dishOptionItem : dishOptionType.getmDishOptionItemSet()) {

                System.out.println("  Option Item : " + dishOptionItem.getmName());
            }
        }
        
        return model;
    }

    @Override
    @RequestMapping(value = "/delMenuItem", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean delMenuItem(HttpSession session, Dish dish) {

        Boolean result = false;
        mDishService.deleteTrd(dish);
        result = true;
        return result;
    }

    @Override
    @RequestMapping(value = "/disableMenuItem", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean disableMenuItem(HttpSession session, Dish dish) {

        Boolean result = false;
        mDishService.disableDish(dish.getmId());
        result = true;
        return result;
    }

    @Override
    @RequestMapping(value = "/enableMenuItem", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean enableMenuItem(HttpSession session, Dish dish) {

        Boolean result = false;
        mDishService.enableDish(dish.getmId());
        result = true;
        return result;
    }

    @Override
    @RequestMapping(value = "/delDishCategory", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object delDishCategory(HttpSession session, String dishCategoryListStr) {

        BaseResultBean result = new BaseResultBean();
        result.setSuccess(false);

        try {
            JSONArray jsonArray = new JSONArray(dishCategoryListStr);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                DishCategory dishCategory = new DishCategory();
                dishCategory.setmId(Long.parseLong((String) jsonObject.get("id")));
                mDishCategoryService.deleteTrd(dishCategory);
            }
            result.setSuccess(true);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 跳转菜单列表页面
     */
    @Override
    @RequestMapping("/showMenuListPage")
    public ModelAndView showMenuListPage(HttpSession session) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        ModelAndView model = new ModelAndView("restaurant/menu/MenuList");

        Map<String, List<MenuItemDetailBean>> menuItemMap = mDishService.getMenuItemMap(restaurant);
        model.addObject("menuItemMap", menuItemMap);
        return model;
    }

    /**
     * 跳转菜单列表页面
     */
    @Override
    @RequestMapping("/showIndigrentListPage")
    public ModelAndView showIndigrentListPage(HttpSession session) {
        ModelAndView model = new ModelAndView("menu/IndigrentList");
        return model;
    }

    /**
     * 跳转相册页面
     */
    @Override
    @RequestMapping("/showDishCategoryEditPage")
    public ModelAndView showDishCategoryEditPage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/menu/DishCategoryEdit");
        List<DishCategoryBean> dishCategoryBeanList =
                mDishCategoryService.getDishCategoryBeanList(session);
        model.addObject("dishCategoryBeanList", dishCategoryBeanList);
        return model;
    }

    @Override
    @RequestMapping("/showCouponListPage")
    public ModelAndView showCouponListPage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/menu/CouponList");

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        List<Coupon> couponList = mCouponService.getCouponListByRestaurantId(restaurant.getmId());
        model.addObject("couponList", couponList);

        return model;
    }

    @Override
    @RequestMapping("/editCouponItem")
    public ModelAndView editCouponItem(HttpSession session, Coupon newCoupon,
            Long couponTargetDishIds[], Long couponExtraCriDishIds[]) throws IOException {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());

        Coupon coupon = mCouponService.findById(Coupon.class, newCoupon.getmId());

        if (couponTargetDishIds != null) {
            Set<Dish> dishSet = new HashSet<Dish>();
            for (Long dishId : couponTargetDishIds) {
                Dish dish = new Dish();
                dish.setmId(dishId);
                dishSet.add(dish);
            }
            coupon.setmCouponTargetDishsSet(dishSet);
        } else {
            Set<Dish> dishSet = new HashSet<Dish>();
            coupon.setmCouponTargetDishsSet(dishSet);
        }

        if (couponExtraCriDishIds != null) {
            Set<Dish> dishSet = new HashSet<Dish>();
            for (Long dishId : couponExtraCriDishIds) {
                Dish dish = new Dish();
                dish.setmId(dishId);
                dishSet.add(dish);
            }
            coupon.setmCouponExtraCriDishsSet(dishSet);
        } else {
            Set<Dish> dishSet = new HashSet<Dish>();
            coupon.setmCouponExtraCriDishsSet(dishSet);
        }

        coupon.setmName(newCoupon.getmName());
        coupon.setmType(newCoupon.getmType());
        coupon.setmTypeAmount(newCoupon.getmTypeAmount());
        coupon.setmTargetType(newCoupon.getmTargetType());
        coupon.setmExtraCriType(newCoupon.getmExtraCriType());
        coupon.setmExtraCriAmount(newCoupon.getmExtraCriAmount());
        coupon.setmDescription(newCoupon.getmDescription());
        coupon.setmTotalQuantity(newCoupon.getmTotalQuantity());
        coupon.setmAvailableStartTime(newCoupon.getmAvailableStartTime());
        coupon.setmAvailableEndTime(newCoupon.getmAvailableEndTime());
        coupon.setmEffectiveDate(newCoupon.getmEffectiveDate());
        coupon.setmMaturityDate(newCoupon.getmMaturityDate());
        coupon.setmWeeks(newCoupon.getmWeeks());

        mCouponService.updateTrd(coupon);

        return new ModelAndView("redirect:./showCouponListPage");
    }

    @Override
    @RequestMapping("/addCouponItem")
    public ModelAndView addCouponItem(HttpSession session, Coupon coupon,
            Long couponTargetDishIds[], Long couponExtraCriDishIds[]) throws IOException {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        coupon.setmRestaurant(account.getmMerchant().getmRestaurant());

        if (couponTargetDishIds != null) {
            Set<Dish> dishSet = new HashSet<Dish>();
            for (Long dishId : couponTargetDishIds) {
                Dish dish = new Dish();
                dish.setmId(dishId);
                dishSet.add(dish);
            }
            coupon.setmCouponTargetDishsSet(dishSet);
        }

        if (couponExtraCriDishIds != null) {
            Set<Dish> dishSet = new HashSet<Dish>();
            for (Long dishId : couponExtraCriDishIds) {
                Dish dish = new Dish();
                dish.setmId(dishId);
                dishSet.add(dish);
            }
            coupon.setmCouponExtraCriDishsSet(dishSet);
        }

        coupon.setmQuantity(coupon.getmTotalQuantity());
        coupon.setmStatus(ParameterConstant.COUPON_STATUS_ENABLE);
        coupon.setmCreateTimestamp(TimestampUtil.getCurrentTimestamp());

        mCouponService.saveTrd(coupon);

        return new ModelAndView("redirect:./showCouponListPage");
    }

    @Override
    @RequestMapping(value = "/getCouponById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getCouponById(HttpSession session, Long mId) {

        CouponBean couponBean = mCouponService.getCouponById(mId);

        return couponBean;
    }

    @Override
    @RequestMapping(value = "/getDishBeanList", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getDishBeanList(HttpSession session) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        List<DishBean> dishBeanList = mDishService.getDishBeanList(restaurant);

        return dishBeanList;
    }

    @Override
    @RequestMapping(value = "/delCouponItem", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean delCouponItem(HttpSession session, Long mId[]) {

        Boolean result = false;

        if (mId != null) {

            for (Long couponId : mId) {

                Coupon coupon = new Coupon();
                coupon.setmId(couponId);
                mDishService.deleteTrd(coupon);
            }

            result = true;
        }

        return result;
    }

    @Override
    @RequestMapping(value = "/addMenuItem", method = RequestMethod.POST)
    public ModelAndView addMenuItem(HttpSession session, Dish dish, String base64Str, String width,
            String height) throws Exception {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        // dish.getmDishCategory().setmRestaurant(account.getmMerchant().getmRestaurant());

        // Get restaurant folder
        String folderName = account.getmMerchant().getmRestaurant().getmFolderName();
        String imageName = uploadImageName(folderName, base64Str, width, height);
        if (!imageName.equals("")) {
            dish.setmImageName(imageName);
        }
        dish.setmCreateTimestamp(TimestampUtil.getCurrentTimestamp());
        dish.setmStatus(ParameterConstant.DISH_STATUS_ENABLE);
        mDishService.saveTrd(dish);

        return new ModelAndView("redirect:./showMenuListPage");
    }

    @Override
    @RequestMapping(value = "/editMenuItem", method = RequestMethod.POST)
    public ModelAndView eidtMenuItem(HttpSession session, Dish dish, String base64Str,
            String width, String height) throws Exception {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Dish oriDish = mDishService.findById(Dish.class, dish.getmId());

        // Get restaurant folder
        String folderName = account.getmMerchant().getmRestaurant().getmFolderName();
        String imageName = uploadImageName(folderName, base64Str, width, height);
        if (!imageName.equals("")) {
            oriDish.setmImageName(imageName);
        }
        oriDish.setmDishCategory(dish.getmDishCategory());
        oriDish.setmName(dish.getmName());
        oriDish.setmCustomization(dish.getmCustomization());
        oriDish.setmPrice(dish.getmPrice());
        oriDish.setmDescription(dish.getmDescription());

        mDishService.updateTrd(oriDish);

        return new ModelAndView("redirect:./showMenuListPage");
    }

    @Override
    @RequestMapping("/showStatementPage")
    public ModelAndView showStatementPage(HttpSession session) {

        ModelAndView model = new ModelAndView("restaurant/statement/Statement");

        return model;
    }

    @Override
    @RequestMapping(value = "/queryStatement", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object queryStatement(HttpSession session, String fromDateTime, String toDateTime) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        OrderFormQueryCondition condition = new OrderFormQueryCondition();
        condition.setRestaurantId(restaurant.getmId());
        condition.setStatus(ParameterConstant.ORDER_FORM_STATUS_FINISHED);
        if (!TextUtils.isEmpty(fromDateTime))
            condition.setFromTimestamp(TimestampUtil.strToDate(fromDateTime,
                    DateFormatType.DateTime));
        if (!TextUtils.isEmpty(toDateTime))
            condition.setToTimestamp(TimestampUtil.strToDate(toDateTime, DateFormatType.DateTime));

        List data = mOrderFormService.listStatementByCondition(condition);
        return data;
    }

    @Override
    @RequestMapping(value = "/queryPaymentPie", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object queryPaymentPie(HttpSession session, String fromDateTime, String toDateTime) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        OrderFormQueryCondition condition = new OrderFormQueryCondition();
        condition.setRestaurantId(restaurant.getmId());
        condition.setStatus(ParameterConstant.ORDER_FORM_STATUS_FINISHED);
        if (!TextUtils.isEmpty(fromDateTime))
            condition.setFromTimestamp(TimestampUtil.strToDate(fromDateTime,
                    DateFormatType.DateTime));
        if (!TextUtils.isEmpty(toDateTime))
            condition.setToTimestamp(TimestampUtil.strToDate(toDateTime, DateFormatType.DateTime));

        List data = mOrderFormService.listPaymentPieByCondition(condition);
        return data;
    }

    @Override
    @RequestMapping(value = "/queryPaymentAxe", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object queryPaymentAxe(HttpSession session, String fromDateTime, String toDateTime) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        OrderFormQueryCondition condition = new OrderFormQueryCondition();
        condition.setRestaurantId(restaurant.getmId());
        condition.setStatus(ParameterConstant.ORDER_FORM_STATUS_FINISHED);
        if (!TextUtils.isEmpty(fromDateTime))
            condition.setFromTimestamp(TimestampUtil.strToDate(fromDateTime,
                    DateFormatType.DateTime));
        if (!TextUtils.isEmpty(toDateTime))
            condition.setToTimestamp(TimestampUtil.strToDate(toDateTime, DateFormatType.DateTime));

        List data = new ArrayList();
        List data1 = mOrderFormService.listAxeByCondition(condition);
        List data2 = mOrderFormService.listLineByCondition(condition);

        data.add(data1);
        data.add(data2);

        return data;
    }



    private String uploadImageName(String folderName, String base64Str, String width, String height)
            throws IOException {

        if (!base64Str.equals("")) {

            // Generate image name
            String mimetype = AwsS3Utils.getMineType(base64Str);
            String extension = MimeUtils.guessExtensionFromMimeType(mimetype);

            // upload image to s3
            InputStream is = ImageUtils.getInputStreamFromBase64(base64Str);

            BufferedImage bi = ImageIO.read(new BufferedInputStream(is));
            // int height270 = ImageUtils.getTargetHeight(bi, ImageUtils.IMAGE_WIDTH_270);
            int height540 = ImageUtils.getTargetHeight(bi, ImageUtils.IMAGE_WIDTH_540);
            // int height1080 = ImageUtils.getTargetHeight(bi, ImageUtils.IMAGE_WIDTH_1080);

            // String imageName = AwsS3Utils.generateImageName(width, height, extension);
            // String imageName270 =
            // AwsS3Utils.generateImageName(String.valueOf(ImageUtils.IMAGE_WIDTH_270),
            // String.valueOf(height270), extension);
            String imageName540 =
                    AwsS3Utils.generateImageName(String.valueOf(ImageUtils.IMAGE_WIDTH_540),
                            String.valueOf(height540), extension);
            // String imageName1080 =
            // AwsS3Utils.generateImageName(String.valueOf(ImageUtils.IMAGE_WIDTH_1080),
            // String.valueOf(height1080), extension);

            // BufferedImage bi270 = ImageUtils.resize(bi, ImageUtils.IMAGE_WIDTH_270, height270);
            BufferedImage bi540 = ImageUtils.resize(bi, ImageUtils.IMAGE_WIDTH_540, height540);
            // BufferedImage bi1080 = ImageUtils.resize(bi, ImageUtils.IMAGE_WIDTH_1080,
            // height1080);

            // InputStream is270 = ImageUtils.getInputStreamFromBufferedImage(bi270, extension);
            InputStream is540 = ImageUtils.getInputStreamFromBufferedImage(bi540, extension);
            // InputStream is1080 = ImageUtils.getInputStreamFromBufferedImage(bi1080, extension);

            ObjectMetadata metaddata = new ObjectMetadata();
            metaddata.setContentType(mimetype);
            metaddata.setContentLength(is.available());
            // ObjectMetadata metaddata270 = new ObjectMetadata();
            // metaddata.setContentType(mimetype);
            // metaddata.setContentLength(is270.available());
            ObjectMetadata metaddata540 = new ObjectMetadata();
            metaddata.setContentType(mimetype);
            metaddata.setContentLength(is540.available());
            // ObjectMetadata metaddata1080 = new ObjectMetadata();
            // metaddata.setContentType(mimetype);
            // metaddata.setContentLength(is1080.available());
            // AwsS3Utils.uploadFileToAwsS3RestaurantRes(folderName, imageName, is, metaddata);
            // AwsS3Utils.uploadFileToAwsS3RestaurantRes(folderName, imageName270, is270,
            // metaddata270);
            AwsS3Utils
                    .uploadFileToAwsS3RestaurantRes(folderName, imageName540, is540, metaddata540);
            // AwsS3Utils.uploadFileToAwsS3RestaurantRes(folderName, imageName1080, is1080,
            // metaddata1080);

            return imageName540;
        }

        return "";
    }

    @Override
    @RequestMapping("/showRestaurantProfilePage")
    public ModelAndView showRestaurantProfilePage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/RestaurantProfile");

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        model.addObject("restaurant", restaurant);

        String logoUrl = "";
        if (!TextUtils.isEmpty(restaurant.getmLogo())) {
            logoUrl =
                    AwsS3Utils.getAwsS3RestaurantResUrl() + restaurant.getmFolderName() + "/"
                            + restaurant.getmLogo();
        }
        model.addObject("logoUrl", logoUrl);

        return model;
    }


    @Override
    @RequestMapping(value = "/editRestaurantProfile", method = RequestMethod.POST)
    public ModelAndView editRestaurantDetails(HttpSession session, Restaurant curRestaurant,
            String base64Str, String width, String height) throws Exception {

        ModelAndView model = new ModelAndView();

        // restaurant.setmStatus(ParameterConstant.RESTAURANT_STATUS_PENDING); //TODO
        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant oriRestaurant = account.getmMerchant().getmRestaurant();

        if (!base64Str.equals("")) {

            // get a file name
            String folderName = oriRestaurant.getmFolderName();

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

            oriRestaurant.setmLogo(imageName);
        }

        oriRestaurant.setmName(curRestaurant.getmName());
        oriRestaurant.setmIsDelivery(curRestaurant.getmIsDelivery());
        oriRestaurant.setmIsPickOut(curRestaurant.getmIsPickOut());
        oriRestaurant.setmIsPreOrder(curRestaurant.getmIsPreOrder());
        oriRestaurant.setmDeliveryFee(curRestaurant.getmDeliveryFee());
        oriRestaurant.setmFreeDeliveryLimit(curRestaurant.getmFreeDeliveryLimit());
        oriRestaurant.setmFaxNum(curRestaurant.getmFaxNum());
        oriRestaurant.setmEmail(curRestaurant.getmEmail());
        oriRestaurant.setmStreet(curRestaurant.getmStreet());
        oriRestaurant.setmCity(curRestaurant.getmCity());
        oriRestaurant.setmState(curRestaurant.getmState());
        oriRestaurant.setmZipCode(curRestaurant.getmZipCode());
        oriRestaurant.setmAddress(curRestaurant.getmAddress());

        //
        oriRestaurant.setmMinDeliveryTotal(curRestaurant.getmMinDeliveryTotal());
        oriRestaurant.setmDeliveryDistance(curRestaurant.getmDeliveryDistance());

        // Delivery Time
        oriRestaurant.setmMonDeliveryStartTime(curRestaurant.getmMonDeliveryStartTime());
        oriRestaurant.setmMonDeliveryEndTime(curRestaurant.getmMonDeliveryEndTime());

        oriRestaurant.setmTueDeliveryStartTime(curRestaurant.getmTueDeliveryStartTime());
        oriRestaurant.setmTueDeliveryEndTime(curRestaurant.getmTueDeliveryEndTime());

        oriRestaurant.setmWedDeliveryStartTime(curRestaurant.getmWedDeliveryStartTime());
        oriRestaurant.setmWedDeliveryEndTime(curRestaurant.getmWedDeliveryEndTime());

        oriRestaurant.setmThuDeliveryStartTime(curRestaurant.getmThuDeliveryStartTime());
        oriRestaurant.setmThuDeliveryEndTime(curRestaurant.getmThuDeliveryEndTime());

        oriRestaurant.setmFriDeliveryStartTime(curRestaurant.getmFriDeliveryStartTime());
        oriRestaurant.setmFriDeliveryEndTime(curRestaurant.getmFriDeliveryEndTime());

        oriRestaurant.setmSatDeliveryStartTime(curRestaurant.getmSatDeliveryStartTime());
        oriRestaurant.setmSatDeliveryEndTime(curRestaurant.getmSatDeliveryEndTime());

        oriRestaurant.setmSunDeliveryStartTime(curRestaurant.getmSunDeliveryStartTime());
        oriRestaurant.setmSunDeliveryEndTime(curRestaurant.getmSunDeliveryEndTime());

        // Open Time
        oriRestaurant.setmMonOpenStartTime(curRestaurant.getmMonOpenStartTime());
        oriRestaurant.setmMonOpenEndTime(curRestaurant.getmMonOpenEndTime());

        oriRestaurant.setmTueOpenStartTime(curRestaurant.getmTueOpenStartTime());
        oriRestaurant.setmTueOpenEndTime(curRestaurant.getmTueOpenEndTime());

        oriRestaurant.setmWedOpenStartTime(curRestaurant.getmWedOpenStartTime());
        oriRestaurant.setmWedOpenEndTime(curRestaurant.getmWedOpenEndTime());

        oriRestaurant.setmThuOpenStartTime(curRestaurant.getmThuOpenStartTime());
        oriRestaurant.setmThuOpenEndTime(curRestaurant.getmThuOpenEndTime());

        oriRestaurant.setmFriOpenStartTime(curRestaurant.getmFriOpenStartTime());
        oriRestaurant.setmFriOpenEndTime(curRestaurant.getmFriOpenEndTime());

        oriRestaurant.setmSatOpenStartTime(curRestaurant.getmSatOpenStartTime());
        oriRestaurant.setmSatOpenEndTime(curRestaurant.getmSatOpenEndTime());

        oriRestaurant.setmSunOpenStartTime(curRestaurant.getmSunOpenStartTime());
        oriRestaurant.setmSunOpenEndTime(curRestaurant.getmSunOpenEndTime());

        oriRestaurant.setmDescription(curRestaurant.getmDescription());

        account.getmMerchant().setmRestaurant(oriRestaurant);

        mAccountService.updateTrd(account);

        // 跳转到审核页面
        model.setViewName("redirect:../menuController/showMenuListPage");

        return model;

    }

    @Override
    @RequestMapping("/showSettingsPage")
    public ModelAndView showSettingsPage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/Settings");

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant restaurant = account.getmMerchant().getmRestaurant();

        model.addObject("restaurant", restaurant);

        return model;
    }

    @Override
    @RequestMapping(value = "/disableRestaurant", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean disableRestaurant(HttpSession session, Restaurant restaurant) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant oriRestaurant = account.getmMerchant().getmRestaurant();

        Boolean result = false;
        mRestaurantService.updateRestaurantStatus(restaurant.getmId(),
                ParameterConstant.RESTAURANT_STATUS_DISABLE);
        oriRestaurant.setmStatus(ParameterConstant.RESTAURANT_STATUS_DISABLE);
        result = true;
        return result;
    }

    @Override
    @RequestMapping(value = "/enableRestaurant", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Boolean enableRestaurant(HttpSession session, Restaurant restaurant) {

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());
        Restaurant oriRestaurant = account.getmMerchant().getmRestaurant();

        Boolean result = false;
        mRestaurantService.updateRestaurantStatus(restaurant.getmId(),
                ParameterConstant.RESTAURANT_STATUS_ENABLE);
        oriRestaurant.setmStatus(ParameterConstant.RESTAURANT_STATUS_ENABLE);
        result = true;
        return result;
    }


    @Override
    @RequestMapping("/showChangePasswordPage")
    public ModelAndView showChangePasswordPage(HttpSession session) {
        ModelAndView model = new ModelAndView("restaurant/ChangePassword");

        return model;
    }

    @Override
    @RequestMapping("/changePassword")
    public ModelAndView changePassword(HttpSession session, String oriPassword, String curPassword) {

        ModelAndView model = new ModelAndView("restaurant/ChangePassword");

        Account account = (Account) session.getAttribute(SessionKey.LoginBean.toString());

        if (mAccountService.changePassword(account, oriPassword, curPassword)) {

            session.setAttribute(SessionKey.LoginBean.toString(), null);
            session.invalidate();

            model.addObject("message", "Change password success. Please Login again.");
            model.setViewName("Login");

        } else {

            model.addObject("message", "Change password Faild.");
        }

        return model;
    }

}
