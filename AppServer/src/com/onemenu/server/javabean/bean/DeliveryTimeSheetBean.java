package com.onemenu.server.javabean.bean;

import java.util.List;

import com.onemenu.server.model.DeliveryTimeSheet;
import com.onemenu.server.util.TimestampUtil;

public class DeliveryTimeSheetBean {

    private String id;
    private String availableDate;
    private String availableStartTime;
    private String availableEndTime;
    private String availableQuantity;
    private String quantity;

    private String createDateTime;

    private List<DriverBean> driverBeanList;

    public DeliveryTimeSheetBean() {

    }

    public DeliveryTimeSheetBean(DeliveryTimeSheet deliveryTimeSheet) {

        this.id = String.valueOf(deliveryTimeSheet.getmId());
        if (deliveryTimeSheet.getmAvailableDate() != null)
            this.availableDate = deliveryTimeSheet.getmAvailableDate();
        if (deliveryTimeSheet.getmAvailableStartTime() != null)
            this.availableStartTime = deliveryTimeSheet.getmAvailableStartTime();
        if (deliveryTimeSheet.getmAvailableEndTime() != null)
            this.availableEndTime = deliveryTimeSheet.getmAvailableEndTime();
        if (deliveryTimeSheet.getmAvailableQuantity() != null)
            this.availableQuantity = String.valueOf(deliveryTimeSheet.getmAvailableQuantity());
        if (deliveryTimeSheet.getmQuantity() != null)
            this.quantity = String.valueOf(deliveryTimeSheet.getmQuantity());
        if (deliveryTimeSheet.getmCreateTimestamp() != null)
            this.createDateTime =
                    TimestampUtil.formatTimestamp(deliveryTimeSheet.getmCreateTimestamp());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(String availableDate) {
        this.availableDate = availableDate;
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

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(String createDateTime) {
        this.createDateTime = createDateTime;
    }

    public List<DriverBean> getDriverBeanList() {
        return driverBeanList;
    }

    public void setDriverBeanList(List<DriverBean> driverBeanList) {
        this.driverBeanList = driverBeanList;
    }

}
