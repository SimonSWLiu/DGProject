package com.onemenu.server.javabean.bean;

import com.onemenu.server.model.Restaurant;
import com.onemenu.server.util.TimestampUtil;

public class RestaurantBean {

    private String id;
    private String name;
    private String faxNum;
    private String email;
    private String phone;
    private String address;
    private String logoUrl;
    private String status;
    private String createTimestamp;

    private String description;
    private String freeDeliveryLimit;
    private String deliveryFee;
    private String deliveryDistance;
    private String minDeliveryTotal;
    private String monDeliveryStartTime;
    private String monDeliveryEndTime;
    private String tueDeliveryStartTime;
    private String tueDeliveryEndTime;
    private String wedDeliveryStartTime;
    private String wedDeliveryEndTime;
    private String thuDeliveryStartTime;
    private String thuDeliveryEndTime;
    private String friDeliveryStartTime;
    private String friDeliveryEndTime;
    private String satDeliveryStartTime;
    private String satDeliveryEndTime;
    private String sunDeliveryStartTime;
    private String sunDeliveryEndTime;
    private String monOpenStartTime;
    private String monOpenEndTime;
    private String tueOpenStartTime;
    private String tueOpenEndTime;
    private String wedOpenStartTime;
    private String wedOpenEndTime;
    private String thuOpenStartTime;
    private String thuOpenEndTime;
    private String friOpenStartTime;
    private String friOpenEndTime;
    private String satOpenStartTime;
    private String satOpenEndTime;
    private String sunOpenStartTime;
    private String sunOpenEndTime;

    public RestaurantBean() {

    }

    public RestaurantBean(Restaurant restaurant) {

        this.id = String.valueOf(restaurant.getmId());
        if (restaurant.getmName() != null)
            this.name = restaurant.getmName();
        if (restaurant.getmFaxNum() != null)
            this.faxNum = restaurant.getmFaxNum();
        if (restaurant.getmEmail() != null)
            this.email = restaurant.getmEmail();
        if (restaurant.getmPhone() != null)
            this.phone = restaurant.getmPhone();
        if (restaurant.getmAddress() != null)
            this.address = restaurant.getmAddress();
        if (restaurant.getmLogo() != null)
            this.logoUrl = restaurant.getmLogo();
        if (restaurant.getmStatus() != null)
            this.status = String.valueOf(restaurant.getmStatus());
        if (restaurant.getmCreateTimestamp() != null)
            this.createTimestamp = TimestampUtil.formatTimestamp(restaurant.getmCreateTimestamp());
        if (restaurant.getmCreateTimestamp() != null)
            this.description = restaurant.getmDescription();

        if (restaurant.getmFreeDeliveryLimit() != null)
            this.freeDeliveryLimit = String.valueOf(restaurant.getmFreeDeliveryLimit());
        if (restaurant.getmDeliveryFee() != null)
            this.deliveryFee = String.valueOf(restaurant.getmDeliveryFee());
        if (restaurant.getmDeliveryDistance() != null)
            this.deliveryDistance = String.valueOf(restaurant.getmDeliveryDistance());
        if (restaurant.getmMinDeliveryTotal() != null)
            this.minDeliveryTotal = String.valueOf(restaurant.getmMinDeliveryTotal());

        if (restaurant.getmMonDeliveryStartTime() != null)
            this.monDeliveryStartTime = restaurant.getmMonDeliveryStartTime();
        if (restaurant.getmMonDeliveryEndTime() != null)
            this.monDeliveryEndTime = restaurant.getmMonDeliveryEndTime();
        if (restaurant.getmTueDeliveryStartTime() != null)
            this.tueDeliveryStartTime = restaurant.getmTueDeliveryStartTime();
        if (restaurant.getmTueDeliveryEndTime() != null)
            this.tueDeliveryEndTime = restaurant.getmTueDeliveryEndTime();
        if (restaurant.getmWedDeliveryStartTime() != null)
            this.wedDeliveryStartTime = restaurant.getmWedDeliveryStartTime();
        if (restaurant.getmWedDeliveryEndTime() != null)
            this.wedDeliveryEndTime = restaurant.getmWedDeliveryEndTime();
        if (restaurant.getmThuDeliveryStartTime() != null)
            this.thuDeliveryStartTime = restaurant.getmThuDeliveryStartTime();
        if (restaurant.getmThuDeliveryEndTime() != null)
            this.thuDeliveryEndTime = restaurant.getmThuDeliveryEndTime();
        if (restaurant.getmFriDeliveryStartTime() != null)
            this.friDeliveryStartTime = restaurant.getmFriDeliveryStartTime();
        if (restaurant.getmFriDeliveryEndTime() != null)
            this.friDeliveryEndTime = restaurant.getmFriDeliveryEndTime();
        if (restaurant.getmSatDeliveryStartTime() != null)
            this.satDeliveryStartTime = restaurant.getmSatDeliveryStartTime();
        if (restaurant.getmSatDeliveryEndTime() != null)
            this.satDeliveryEndTime = restaurant.getmSatDeliveryEndTime();
        if (restaurant.getmSunDeliveryStartTime() != null)
            this.sunDeliveryStartTime = restaurant.getmSunDeliveryStartTime();
        if (restaurant.getmSunDeliveryEndTime() != null)
            this.sunDeliveryEndTime = restaurant.getmSunDeliveryEndTime();
        if (restaurant.getmMonOpenStartTime() != null)
            this.monOpenStartTime = restaurant.getmMonOpenStartTime();
        if (restaurant.getmMonOpenEndTime() != null)
            this.monOpenEndTime = restaurant.getmMonOpenEndTime();
        if (restaurant.getmTueOpenStartTime() != null)
            this.tueOpenStartTime = restaurant.getmTueOpenStartTime();
        if (restaurant.getmTueOpenEndTime() != null)
            this.tueOpenEndTime = restaurant.getmTueOpenEndTime();
        if (restaurant.getmWedDeliveryStartTime() != null)
            this.wedOpenStartTime = restaurant.getmWedDeliveryStartTime();
        if (restaurant.getmWedDeliveryEndTime() != null)
            this.wedOpenEndTime = restaurant.getmWedDeliveryEndTime();
        if (restaurant.getmThuOpenStartTime() != null)
            this.thuOpenStartTime = restaurant.getmThuOpenStartTime();
        if (restaurant.getmThuOpenEndTime() != null)
            this.thuOpenEndTime = restaurant.getmThuOpenEndTime();
        if (restaurant.getmFriOpenStartTime() != null)
            this.friOpenStartTime = restaurant.getmFriOpenStartTime();
        if (restaurant.getmFriOpenEndTime() != null)
            this.friOpenEndTime = restaurant.getmFriOpenEndTime();
        if (restaurant.getmSatDeliveryStartTime() != null)
            this.satOpenStartTime = restaurant.getmSatDeliveryStartTime();
        if (restaurant.getmSatDeliveryEndTime() != null)
            this.satOpenEndTime = restaurant.getmSatDeliveryEndTime();
        if (restaurant.getmSunDeliveryStartTime() != null)
            this.sunOpenStartTime = restaurant.getmSunDeliveryStartTime();
        if (restaurant.getmSunDeliveryEndTime() != null)
            this.sunOpenEndTime = restaurant.getmSunDeliveryEndTime();
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

    public String getFaxNum() {
        return faxNum;
    }

    public void setFaxNum(String faxNum) {
        this.faxNum = faxNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFreeDeliveryLimit() {
        return freeDeliveryLimit;
    }

    public void setFreeDeliveryLimit(String freeDeliveryLimit) {
        this.freeDeliveryLimit = freeDeliveryLimit;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(String deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public String getMinDeliveryTotal() {
        return minDeliveryTotal;
    }

    public void setMinDeliveryTotal(String minDeliveryTotal) {
        this.minDeliveryTotal = minDeliveryTotal;
    }

    public String getMonDeliveryStartTime() {
        return monDeliveryStartTime;
    }

    public void setMonDeliveryStartTime(String monDeliveryStartTime) {
        this.monDeliveryStartTime = monDeliveryStartTime;
    }

    public String getMonDeliveryEndTime() {
        return monDeliveryEndTime;
    }

    public void setMonDeliveryEndTime(String monDeliveryEndTime) {
        this.monDeliveryEndTime = monDeliveryEndTime;
    }

    public String getTueDeliveryStartTime() {
        return tueDeliveryStartTime;
    }

    public void setTueDeliveryStartTime(String tueDeliveryStartTime) {
        this.tueDeliveryStartTime = tueDeliveryStartTime;
    }

    public String getTueDeliveryEndTime() {
        return tueDeliveryEndTime;
    }

    public void setTueDeliveryEndTime(String tueDeliveryEndTime) {
        this.tueDeliveryEndTime = tueDeliveryEndTime;
    }

    public String getWedDeliveryStartTime() {
        return wedDeliveryStartTime;
    }

    public void setWedDeliveryStartTime(String wedDeliveryStartTime) {
        this.wedDeliveryStartTime = wedDeliveryStartTime;
    }

    public String getWedDeliveryEndTime() {
        return wedDeliveryEndTime;
    }

    public void setWedDeliveryEndTime(String wedDeliveryEndTime) {
        this.wedDeliveryEndTime = wedDeliveryEndTime;
    }

    public String getThuDeliveryStartTime() {
        return thuDeliveryStartTime;
    }

    public void setThuDeliveryStartTime(String thuDeliveryStartTime) {
        this.thuDeliveryStartTime = thuDeliveryStartTime;
    }

    public String getThuDeliveryEndTime() {
        return thuDeliveryEndTime;
    }

    public void setThuDeliveryEndTime(String thuDeliveryEndTime) {
        this.thuDeliveryEndTime = thuDeliveryEndTime;
    }

    public String getFriDeliveryStartTime() {
        return friDeliveryStartTime;
    }

    public void setFriDeliveryStartTime(String friDeliveryStartTime) {
        this.friDeliveryStartTime = friDeliveryStartTime;
    }

    public String getFriDeliveryEndTime() {
        return friDeliveryEndTime;
    }

    public void setFriDeliveryEndTime(String friDeliveryEndTime) {
        this.friDeliveryEndTime = friDeliveryEndTime;
    }

    public String getSatDeliveryStartTime() {
        return satDeliveryStartTime;
    }

    public void setSatDeliveryStartTime(String satDeliveryStartTime) {
        this.satDeliveryStartTime = satDeliveryStartTime;
    }

    public String getSatDeliveryEndTime() {
        return satDeliveryEndTime;
    }

    public void setSatDeliveryEndTime(String satDeliveryEndTime) {
        this.satDeliveryEndTime = satDeliveryEndTime;
    }

    public String getSunDeliveryStartTime() {
        return sunDeliveryStartTime;
    }

    public void setSunDeliveryStartTime(String sunDeliveryStartTime) {
        this.sunDeliveryStartTime = sunDeliveryStartTime;
    }

    public String getSunDeliveryEndTime() {
        return sunDeliveryEndTime;
    }

    public void setSunDeliveryEndTime(String sunDeliveryEndTime) {
        this.sunDeliveryEndTime = sunDeliveryEndTime;
    }

    public String getMonOpenStartTime() {
        return monOpenStartTime;
    }

    public void setMonOpenStartTime(String monOpenStartTime) {
        this.monOpenStartTime = monOpenStartTime;
    }

    public String getMonOpenEndTime() {
        return monOpenEndTime;
    }

    public void setMonOpenEndTime(String monOpenEndTime) {
        this.monOpenEndTime = monOpenEndTime;
    }

    public String getTueOpenStartTime() {
        return tueOpenStartTime;
    }

    public void setTueOpenStartTime(String tueOpenStartTime) {
        this.tueOpenStartTime = tueOpenStartTime;
    }

    public String getTueOpenEndTime() {
        return tueOpenEndTime;
    }

    public void setTueOpenEndTime(String tueOpenEndTime) {
        this.tueOpenEndTime = tueOpenEndTime;
    }

    public String getWedOpenStartTime() {
        return wedOpenStartTime;
    }

    public void setWedOpenStartTime(String wedOpenStartTime) {
        this.wedOpenStartTime = wedOpenStartTime;
    }

    public String getWedOpenEndTime() {
        return wedOpenEndTime;
    }

    public void setWedOpenEndTime(String wedOpenEndTime) {
        this.wedOpenEndTime = wedOpenEndTime;
    }

    public String getThuOpenStartTime() {
        return thuOpenStartTime;
    }

    public void setThuOpenStartTime(String thuOpenStartTime) {
        this.thuOpenStartTime = thuOpenStartTime;
    }

    public String getThuOpenEndTime() {
        return thuOpenEndTime;
    }

    public void setThuOpenEndTime(String thuOpenEndTime) {
        this.thuOpenEndTime = thuOpenEndTime;
    }

    public String getFriOpenStartTime() {
        return friOpenStartTime;
    }

    public void setFriOpenStartTime(String friOpenStartTime) {
        this.friOpenStartTime = friOpenStartTime;
    }

    public String getFriOpenEndTime() {
        return friOpenEndTime;
    }

    public void setFriOpenEndTime(String friOpenEndTime) {
        this.friOpenEndTime = friOpenEndTime;
    }

    public String getSatOpenStartTime() {
        return satOpenStartTime;
    }

    public void setSatOpenStartTime(String satOpenStartTime) {
        this.satOpenStartTime = satOpenStartTime;
    }

    public String getSatOpenEndTime() {
        return satOpenEndTime;
    }

    public void setSatOpenEndTime(String satOpenEndTime) {
        this.satOpenEndTime = satOpenEndTime;
    }

    public String getSunOpenStartTime() {
        return sunOpenStartTime;
    }

    public void setSunOpenStartTime(String sunOpenStartTime) {
        this.sunOpenStartTime = sunOpenStartTime;
    }

    public String getSunOpenEndTime() {
        return sunOpenEndTime;
    }

    public void setSunOpenEndTime(String sunOpenEndTime) {
        this.sunOpenEndTime = sunOpenEndTime;
    }

}
