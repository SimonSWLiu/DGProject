package com.onemenu.server.controllerImpl;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.controller.restaurantAdminController;
import com.onemenu.server.javabean.bean.BaseResultBean;
import com.onemenu.server.javabean.bean.RestaurantBean;
import com.onemenu.server.model.Restaurant;
import com.onemenu.server.service.RestaurantService;

/**
 * 
 * @author simonliu
 *
 */
@Controller
@RequestMapping("/restaurantAdminController")
public class restaurantAdminControllerImpl implements restaurantAdminController {

    @Autowired
    private RestaurantService mRestaurantService;

    @Override
    @RequestMapping(value = "/approveRestaurantById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean approveRestaurantById(HttpSession session, Restaurant restaurant) {

        return mRestaurantService.approveRestaurantById(restaurant.getmId());
    }
    
    @Override
    @RequestMapping(value = "/rejectRestaurantById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public boolean rejectRestaurantById(HttpSession session, Restaurant restaurant) {

        return mRestaurantService.rejectRestaurantById(restaurant.getmId());
    }

    @Override
    @RequestMapping(value = "/getRestaurantById", method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getRestaurantById(HttpSession session, Restaurant restaurant) throws Exception {

        RestaurantBean restaurantBean = mRestaurantService.getRestaurantBeanById(restaurant.getmId());

        return restaurantBean;
    }
    
    @Override
    @RequestMapping("/approveRestaurant")
    @ResponseBody
    public Object approveRestaurant(String restaurantListStr) {

        BaseResultBean result = new BaseResultBean();
        result.setSuccess(false);

        try {
            JSONArray jsonArray = new JSONArray(restaurantListStr);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(Long.parseLong((String) jsonObject.get("id")));
                mRestaurantService.updateRestaurantStatus(
                        Long.parseLong((String) jsonObject.get("id")),
                        ParameterConstant.RESTAURANT_STATUS_ENABLE);
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

    @Override
    @RequestMapping("/rejectRestaurant")
    @ResponseBody
    public boolean rejectRestaurant(String idListStr) {

        String[] idList = idListStr.split(",");

        for (int i = 0; i < idList.length; i++) {
            mRestaurantService.updateRestaurantStatus(Long.parseLong(idList[i]), ParameterConstant.RESTAURANT_STATUS_DISABLE);
        }

        return true;
    }

    
}
