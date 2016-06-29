package com.onemenu.server.javabean.bean;

import java.util.List;

import com.onemenu.server.model.Dish;
import com.onemenu.server.util.AwsS3Utils;
import com.onemenu.server.util.FormatUtils;


public class DishBean {

    public String id;
    public String name;
    private String otherName;
    private String dishCategoryId;
    public String price;
    public String desc; // 描述
    public String imageUrl;
    public List<DishOptionTypeBean> dishOptionTypeBeanList;
    public String status;

    //
    public String pageView;
    public String purchaseVolume;

    public DishBean() {

    }

    public DishBean(Dish dish) {

        this.id = String.valueOf(dish.getmId());

        if (dish.getmName() != null)
            this.name = dish.getmName();

        if (dish.getmOtherName() != null)
            this.otherName = dish.getmOtherName();

        if (dish.getmDishCategory() != null)
            this.dishCategoryId = String.valueOf(dish.getmDishCategory().getmId());

        if (dish.getmPrice() != null)
            this.price = FormatUtils.formatPrice(dish.getmPrice());

        if (dish.getmDescription() != null)
            this.desc = dish.getmDescription(); // 描述

        if (dish.getmImageName() != null)
            this.imageUrl =
                    AwsS3Utils.getAwsS3RestaurantResUrl()
                            + dish.getmDishCategory().getmRestaurant().getmFolderName() + "/"
                            + dish.getmImageName();

        if (dish.getmDishOptionTypeSet() != null)
            this.dishOptionTypeBeanList =
                    new DishOptionTypeBean().toDishOptionTypeBeanList(dish.getmDishOptionTypeSet());

        if (dish.getmName() != null)
            this.status = dish.getmStatus();

        if (dish.getmDishRank() != null) {
            this.pageView = String.valueOf(dish.getmDishRank().getmPageView());
            this.purchaseVolume = String.valueOf(dish.getmDishRank().getmPurchaseVolume());
        }
    }
    
    public void setDishBeanForCoupon(Dish dish) {

        this.id = String.valueOf(dish.getmId());

        if (dish.getmName() != null)
            this.name = dish.getmName();
    }

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<DishOptionTypeBean> getDishOptionTypeBeanList() {
        return dishOptionTypeBeanList;
    }

    public void setDishOptionTypeBeanList(List<DishOptionTypeBean> dishOptionTypeBeanList) {
        this.dishOptionTypeBeanList = dishOptionTypeBeanList;
    }

    public String getDishStatus() {
        return status;
    }

    public void setDishStatus(String dishStatus) {
        this.status = dishStatus;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
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

    public String getPageView() {
        return pageView;
    }

    public void setPageView(String pageView) {
        this.pageView = pageView;
    }

    public String getPurchaseVolume() {
        return purchaseVolume;
    }

    public void setPurchaseVolume(String purchaseVolume) {
        this.purchaseVolume = purchaseVolume;
    }

}
