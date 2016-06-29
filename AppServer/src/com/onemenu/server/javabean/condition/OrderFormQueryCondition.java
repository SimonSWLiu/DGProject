package com.onemenu.server.javabean.condition;

import java.util.Date;

public class OrderFormQueryCondition {

    private Long restaurantId;
    private String status;
    private Date fromTimestamp;
    private Date toTimestamp;
    private Integer isOneMenu;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getFromTimestamp() {
        return fromTimestamp;
    }

    public void setFromTimestamp(Date fromTimestamp) {
        this.fromTimestamp = fromTimestamp;
    }

    public Date getToTimestamp() {
        return toTimestamp;
    }

    public void setToTimestamp(Date toTimestamp) {
        this.toTimestamp = toTimestamp;
    }

    public Integer getIsOneMenu() {
        return isOneMenu;
    }

    public void setIsOneMenu(Integer isOneMenu) {
        this.isOneMenu = isOneMenu;
    }

}
