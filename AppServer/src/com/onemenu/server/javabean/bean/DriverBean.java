package com.onemenu.server.javabean.bean;

import java.util.List;

import com.onemenu.server.model.DeliveryTimeSheet;
import com.onemenu.server.model.Driver;
import com.onemenu.server.model.OrderForm;
import com.onemenu.server.util.TimestampUtil;

public class DriverBean {

    private String driverId;
    private String driverName;
    private String driverPhone;
    private String driverEmail;
    private String driverAddress;
    private String driverAvatar;
    private String status;
    private String createTimestamp;

    private List<OrderForm> mOrderFormList;
    private List<DeliveryTimeSheet> mDeliveryTimeSheetList;

    public DriverBean() {

    }

    public DriverBean(Driver driver) {

        this.driverId = String.valueOf(driver.getmId());
        if (driver.getmName() != null)
            this.driverName = driver.getmName();
        if (driver.getmPhone() != null)
            this.driverPhone = driver.getmPhone();
        if (driver.getmEmail() != null)
            this.driverEmail = driver.getmEmail();
        if (driver.getmAddress() != null)
            this.driverAddress = driver.getmAddress();
        if (driver.getmAvatar() != null)
            this.driverAvatar = driver.getmAvatar();
        if (driver.getmStatus() != null)
            this.status = String.valueOf(driver.getmStatus());
        if (driver.getmCreateTimestamp() != null)
            this.createTimestamp = TimestampUtil.formatTimestamp(driver.getmCreateTimestamp());
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverAvatar() {
        return driverAvatar;
    }

    public void setDriverAvatar(String driverAvatar) {
        this.driverAvatar = driverAvatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderForm> getmOrderFormList() {
        return mOrderFormList;
    }

    public void setmOrderFormList(List<OrderForm> mOrderFormList) {
        this.mOrderFormList = mOrderFormList;
    }

    public List<DeliveryTimeSheet> getmDeliveryTimeSheetList() {
        return mDeliveryTimeSheetList;
    }

    public void setmDeliveryTimeSheetList(List<DeliveryTimeSheet> mDeliveryTimeSheetList) {
        this.mDeliveryTimeSheetList = mDeliveryTimeSheetList;
    }

    public String getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(String createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

}
