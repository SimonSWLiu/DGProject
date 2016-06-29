package com.onemenu.server.javabean.bean;

import org.json.JSONArray;
import org.json.JSONException;

import com.onemenu.server.model.Dish;
import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.FormatUtils;


/**
 * 
 * @author simonliu
 *
 */
public class MenuItemDetailBean {

    private String id;
    private String name;
    private String otherName;
    private String dishCategoryId;
    private String desc;
    private String price;
    private String imageUrl;
    private String customization;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCustomization() {
        return customization;
    }

    public void setCustomization(String customization) {
        this.customization = customization;
    }

    public String getDishCategoryId() {
        return dishCategoryId;
    }

    public void setDishCategoryId(String dishCategoryId) {
        this.dishCategoryId = dishCategoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public void setMenuItemDetailBean(Dish dish) {

        this.id = String.valueOf(dish.getmId());
        this.name = dish.getmName();
        this.otherName = dish.getmOtherName();
        this.dishCategoryId = String.valueOf(dish.getmDishCategory().getmId());
        this.desc = dish.getmDescription();
        this.price = FormatUtils.formatPrice(dish.getmPrice());
        this.imageUrl =
                AwsS3Utils.getAwsS3RestaurantResUrl()
                        + dish.getmDishCategory().getmRestaurant().getmFolderName() + "/"
                        + dish.getmImageName();

        if (dish.getmCustomization() != null) {
            JSONArray customizationJson;
            try {
                customizationJson = new JSONArray(dish.getmCustomization());
                this.customization = customizationJson.toString();
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }

        this.status = dish.getmStatus();
    }
}
