package com.onemenu.server.javabean.bean;

import java.util.List;

public class OrderItemBean {

    public String dishId;
    public String dishName;
    public String dishImageUrl;
    public String dishAmount;
    public String dishPrice;
    public String orderItemPrice; // dishPrice*dishaAmount
    
    public List<DishOptionItemBean> orderItemOptionItemArray;

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishImageUrl() {
        return dishImageUrl;
    }

    public void setDishImageUrl(String dishImageUrl) {
        this.dishImageUrl = dishImageUrl;
    }

    public String getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(String dishAmount) {
        this.dishAmount = dishAmount;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getOrderItemPrice() {
        return orderItemPrice;
    }

    public void setOrderItemPrice(String orderItemPrice) {
        this.orderItemPrice = orderItemPrice;
    }

    public List<DishOptionItemBean> getOrderItemOptionItemArray() {
        return orderItemOptionItemArray;
    }

    public void setOrderItemOptionItemArray(List<DishOptionItemBean> orderItemOptionItemArray) {
        this.orderItemOptionItemArray = orderItemOptionItemArray;
    }

}
