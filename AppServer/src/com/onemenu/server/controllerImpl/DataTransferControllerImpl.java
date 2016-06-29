package com.onemenu.server.controllerImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.onemenu.server.controller.DataTransferController;
import com.onemenu.server.model.Dish;
import com.onemenu.server.model.DishImage;
import com.onemenu.server.model.DishOptionItem;
import com.onemenu.server.model.DishOptionType;
import com.onemenu.server.service.DishService;
import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.TimestampUtil;

/**
 * 
 * @author simonliu
 *
 */
@Controller
@RequestMapping("/dataTransferController")
public class DataTransferControllerImpl implements DataTransferController {

    @Autowired
    private DishService mDishService;

    @Override
    @RequestMapping(value = "/transDishToIngredient", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object transDishToIngredient(HttpSession session) {

        Timestamp curTimestamp = TimestampUtil.getCurrentTimestamp();

        List<Dish> dishList = mDishService.findAll(Dish.class);

        for (Dish dish : dishList) {

            Long dishId = dish.getmId();
            if (dish.getmCustomization() != null) {
                JSONArray customizationJson;
                try {
                    customizationJson = new JSONArray(dish.getmCustomization());

                    for (int i = 0; i < customizationJson.length(); i++) {

                        JSONObject ingredientTypeJO = customizationJson.getJSONObject(i);

                        DishOptionType ingredientType = new DishOptionType();
                        String typeName = ingredientTypeJO.get("type").toString();
                        String isMandatory = ingredientTypeJO.get("isMandatory").toString();
                        String isSingle = ingredientTypeJO.get("isSingle").toString();
                        ingredientType.setmName(typeName);
                        ingredientType.setmIsMandatory(isMandatory);
                        ingredientType.setmIsSingle(isSingle);
                        ingredientType.setmDish(dish);
                        ingredientType.setmCreateTimestamp(curTimestamp);
                        mDishService.saveTrd(ingredientType);

                        JSONArray ingredientJA = ingredientTypeJO.getJSONArray("ingredient");

                        for (int j = 0; j < ingredientJA.length(); j++) {
                            JSONObject ingredientJO = ingredientJA.getJSONObject(j);
                            String ingredientName = ingredientJO.get("ingredientName").toString();
                            String ingredientPrice = ingredientJO.get("ingredientPrice").toString();


                            DishOptionItem ingredientItem = new DishOptionItem();
                            ingredientItem.setmName(ingredientName);
                            try {
                                ingredientItem.setmPrice(new BigDecimal(ingredientPrice));
                            } catch (Exception e) {
                                System.err.println("Problem Dish Id : ------- " + dishId
                                        + " /n Problem Price : ------- " + ingredientPrice);
                            }
                            ingredientItem.setmDishOptionType(ingredientType);
                            ingredientItem.setmCreateTimestamp(curTimestamp);
                            mDishService.saveTrd(ingredientItem);
                        }

                    }

                } catch (JSONException e) {
                    System.err.println("Problem Dish Id : ------- " + dishId);
                }
            }
        }

        return dishList;
    }

    @Override
    @RequestMapping(value = "/transDishImage", method = RequestMethod.GET,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object transDishImage(HttpSession session) {

        Timestamp curTimestamp = TimestampUtil.getCurrentTimestamp();

        List<Dish> dishList = mDishService.findAll(Dish.class);

        for (Dish dish : dishList) {

            Long dishId = dish.getmId();
            String dishName = dish.getmName();
            String dishImageFileName = dish.getmImageName();

            if (dishImageFileName != null) {
                String dishImageUrl =
                        AwsS3Utils.getAwsS3RestaurantResUrl()
                                + dish.getmDishCategory().getmRestaurant().getmFolderName() + "/"
                                + dish.getmImageName();
                String imageUrl = dishImageUrl.replace(AwsS3Utils.getAwsS3BucketUrl(), "");

                String dishIamageName = dishImageFileName.replace(".jpeg", "");
                String[] strArray = dishIamageName.split("_");
                String widthStr = strArray[1];
                String heightStr = strArray[2];
                Integer width = Integer.parseInt(widthStr);
                Integer height = Integer.parseInt(heightStr);

                DishImage dishImage = new DishImage();
                dishImage.setmName(dishImageFileName);
                dishImage.setmUrl(imageUrl);
                dishImage.setmWidth(width);
                dishImage.setmHeight(height);
                dishImage.setmDescription(dishName);
                dishImage.setmCreateTimestamp(curTimestamp);

                Dish dDish = new Dish();
                dDish.setmId(dishId);
                dishImage.setmDish(dDish);

                mDishService.saveTrd(dishImage);
            }
        }

        return true;
    }
}
