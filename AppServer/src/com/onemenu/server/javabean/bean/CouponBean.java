package com.onemenu.server.javabean.bean;

import java.util.ArrayList;
import java.util.List;

import com.onemenu.server.model.Coupon;
import com.onemenu.server.model.Dish;


public class CouponBean {

    private String id;
    private String name;
    private String type;
    private String typeAmount;
    private String targetType;
    private List<DishBean> couponTargetDishsList;
    private String extraCriType;
    private List<DishBean> couponExtraCriDishsList;
    private String extraCriAmount;
    private String description;
    private String quantity;
    private String totalQuantity;
    private String availableStartTime;
    private String availableEndTime;
    private String effectiveDate;
    private String maturityDate;
    private String weeks;
    private String imageUrl;
    private String status;
    private String createTimestamp;

//    private List<DishBean> existingDishsList;
    
    public CouponBean() {

    }

    public CouponBean(Coupon coupon) {

        this.id = String.valueOf(coupon.getmId());

        if (coupon.getmName() != null)
            this.name = coupon.getmName();

        if (coupon.getmType() != null)
            this.type = coupon.getmType();

        if (coupon.getmTypeAmount() != null)
            this.typeAmount = String.valueOf(coupon.getmTypeAmount());

        if (coupon.getmTargetType() != null)
            this.targetType = coupon.getmTargetType();

        if (coupon.getmExtraCriType() != null)
            this.extraCriType = coupon.getmExtraCriType();

        if (coupon.getmExtraCriAmount() != null)
            this.extraCriAmount = String.valueOf(coupon.getmExtraCriAmount());

        if (coupon.getmDescription() != null)
            this.description = coupon.getmDescription();

        if (coupon.getmQuantity() != null)
            this.quantity = String.valueOf(coupon.getmQuantity());

        if (coupon.getmTotalQuantity() != null)
            this.totalQuantity = String.valueOf(coupon.getmTotalQuantity());

        if (coupon.getmAvailableStartTime() != null)
            this.availableStartTime = coupon.getmAvailableStartTime();

        if (coupon.getmAvailableEndTime() != null)
            this.availableEndTime = coupon.getmAvailableEndTime();

        if (coupon.getmEffectiveDate() != null)
            this.effectiveDate = coupon.getmEffectiveDate();

        if (coupon.getmMaturityDate() != null)
            this.maturityDate = coupon.getmMaturityDate();

        if (coupon.getmWeeks() != null)
            this.weeks = coupon.getmWeeks();

        if (coupon.getmImageUrl() != null)
            this.imageUrl = coupon.getmImageUrl();

        if (coupon.getmStatus() != null)
            this.status = coupon.getmStatus();

        if (coupon.getmCreateTimestamp() != null)
            this.createTimestamp = coupon.getmCreateTimestamp().toString();

        this.couponTargetDishsList = new ArrayList<DishBean>();
        for (Dish dish : coupon.getmCouponTargetDishsSet()) {
            
            DishBean dishBean = new DishBean();
            dishBean.setDishBeanForCoupon(dish);
            this.couponTargetDishsList.add(dishBean);
        }

        this.couponExtraCriDishsList = new ArrayList<DishBean>();
        for (Dish dish : coupon.getmCouponExtraCriDishsSet()) {

            DishBean dishBean = new DishBean();
            dishBean.setDishBeanForCoupon(dish);
            this.couponExtraCriDishsList.add(dishBean);
        }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeAmount() {
        return typeAmount;
    }

    public void setTypeAmount(String typeAmount) {
        this.typeAmount = typeAmount;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getExtraCriType() {
        return extraCriType;
    }

    public void setExtraCriType(String extraCriType) {
        this.extraCriType = extraCriType;
    }

    public String getExtraCriAmount() {
        return extraCriAmount;
    }

    public void setExtraCriAmount(String extraCriAmount) {
        this.extraCriAmount = extraCriAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getAvailableStartTime() {
        return availableStartTime;
    }

    public void setAvailableStartTime(String availableStartTime) {
        this.availableStartTime = availableStartTime;
    }

    public String getAvailableEndTime() {
        return availableEndTime;
    }

    public void setAvailableEndTime(String availableEndTime) {
        this.availableEndTime = availableEndTime;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DishBean> getCouponTargetDishsList() {
        return couponTargetDishsList;
    }

    public void setCouponTargetDishsList(List<DishBean> couponTargetDishsList) {
        this.couponTargetDishsList = couponTargetDishsList;
    }

    public List<DishBean> getCouponExtraCriDishsList() {
        return couponExtraCriDishsList;
    }

    public void setCouponExtraCriDishsList(List<DishBean> couponExtraCriDishsList) {
        this.couponExtraCriDishsList = couponExtraCriDishsList;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

//    public List<DishBean> getExistingDishsList() {
//        return existingDishsList;
//    }
//
//    public void setExistingDishsList(List<DishBean> existingDishsList) {
//        this.existingDishsList = existingDishsList;
//    }


}
