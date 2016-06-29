package com.onemenu.server.javabean.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.onemenu.server.model.OrderForm;
import com.onemenu.server.model.OrderItem;
import com.onemenu.server.model.OrderItemIngredient;
import com.onemenu.server.util.FormatUtils;
import com.onemenu.server.util.TimestampUtil;

public class OrderFormBean {

    private String orderFormId;

    private String orderFormCode;
    private String getType;// -
    private String customerName;
    private String customerAddress;
    private String customerPhone;

    private List<OrderItemBean> orderItemArray;
    private String taxFee;
    private String tipsFee; // -1 : cash
    private String deliveryFee;
    private String discountFee;
    private String orderFormPrice;

    private String status;// -

    private String orderFormRestConfirmedDateTime;
    private String orderFormCancelDateTime;
    private String orderFormCallDeliveryDateTime;
    private String orderFormDriverConfirmedDateTime;
    private String orderFormArrivedDateTime;
    private String orderFormCreateDateTime;

    private Date orderFormRestConfirmedTimestamp;
    private Date orderFormCancelTimestamp;
    private Date orderFormCallDeliveryTimestamp;
    private Date orderFormDriverConfirmedTimestamp;
    private Date orderFormArrivedTimestamp;

    private Date orderFormCreateTimestamp;
    private Date curTimestamp;

    public OrderFormBean() {

    }

    public OrderFormBean(OrderForm orderForm) {

        this.orderFormId = String.valueOf(orderForm.getmId());

        if (orderForm.getmTrade() != null) {
            if (orderForm.getmTrade().getmGetType() != null)
                this.getType = String.valueOf(orderForm.getmTrade().getmGetType());
            if (orderForm.getmTrade().getmCustomerName() != null)
                this.customerName = orderForm.getmTrade().getmCustomerName();
            if (orderForm.getmTrade().getmCustomerAddress() != null)
                this.customerAddress = orderForm.getmTrade().getmCustomerAddress();
            if (orderForm.getmTrade().getmCustomerPhone() != null)
                this.customerPhone = orderForm.getmTrade().getmCustomerPhone();
        }
        if (orderForm.getmCode() != null)
            this.orderFormCode = orderForm.getmCode();
        if (orderForm.getmTaxFee() != null)
            this.taxFee = FormatUtils.formatPrice(orderForm.getmTaxFee());
        if (orderForm.getmTipsFee() != null)
            this.tipsFee = FormatUtils.formatPrice(orderForm.getmTipsFee());
        if (orderForm.getmDeliveryFee() != null)
            this.deliveryFee = FormatUtils.formatPrice(orderForm.getmDeliveryFee());
        if (orderForm.getmDiscountFee() != null)
            this.discountFee = FormatUtils.formatPrice(orderForm.getmDiscountFee());
        if (orderForm.getmPrice() != null)
            this.orderFormPrice = FormatUtils.formatPrice(orderForm.getmPrice());

        if (orderForm.getmStatus() != null)
            this.status = orderForm.getmStatus();

        if (orderForm.getmRestConfirmedTimestamp() != null)
            this.orderFormRestConfirmedDateTime =
                    TimestampUtil.formatTimestamp(orderForm.getmRestConfirmedTimestamp());
        if (orderForm.getmCancelTimestamp() != null)
            this.orderFormCancelDateTime =
                    TimestampUtil.formatTimestamp(orderForm.getmCancelTimestamp());
        if (orderForm.getmCallDeliveryTimestamp() != null)
            this.orderFormCallDeliveryDateTime =
                    TimestampUtil.formatTimestamp(orderForm.getmCallDeliveryTimestamp());
        if (orderForm.getmDriverConfirmedTimestamp() != null)
            this.orderFormDriverConfirmedDateTime =
                    TimestampUtil.formatTimestamp(orderForm.getmDriverConfirmedTimestamp());
        if (orderForm.getmArrivedTimestamp() != null)
            this.orderFormArrivedDateTime =
                    TimestampUtil.formatTimestamp(orderForm.getmArrivedTimestamp());
        if (orderForm.getmCreateTimestamp() != null)
            this.orderFormCreateDateTime =
                    TimestampUtil.formatTimestamp(orderForm.getmCreateTimestamp());

        if (orderForm.getmRestConfirmedTimestamp() != null)
            this.orderFormRestConfirmedTimestamp = orderForm.getmRestConfirmedTimestamp();
        if (orderForm.getmCancelTimestamp() != null)
            this.orderFormCancelTimestamp = orderForm.getmCancelTimestamp();
        if (orderForm.getmCallDeliveryTimestamp() != null)
            this.orderFormCallDeliveryTimestamp = orderForm.getmCallDeliveryTimestamp();
        if (orderForm.getmDriverConfirmedTimestamp() != null)
            this.orderFormDriverConfirmedTimestamp = orderForm.getmDriverConfirmedTimestamp();
        if (orderForm.getmArrivedTimestamp() != null)
            this.orderFormArrivedTimestamp = orderForm.getmArrivedTimestamp();
        if (orderForm.getmCreateTimestamp() != null)
            this.orderFormCreateTimestamp = orderForm.getmCreateTimestamp();

        List<OrderItemBean> orderItemBeanList = new ArrayList<OrderItemBean>();
        for (OrderItem orderItem : orderForm.getmOrderItemSet()) {

            OrderItemBean orderItemBean = new OrderItemBean();

            orderItemBean.setDishId(String.valueOf(orderItem.getmId()));;
            if (orderItem.getmDishName() != null)
                orderItemBean.setDishName(orderItem.getmDishName());;
            if (orderItem.getmDishImageUrl() != null)
                orderItemBean.setDishImageUrl(orderItem.getmDishImageUrl());;
            if (orderItem.getmDishAmount() != null)
                orderItemBean.setDishAmount(orderItem.getmDishAmount());;
            if (orderItem.getmDishPrice() != null)
                orderItemBean.setDishPrice(orderItem.getmDishPrice());;
            if (orderItem.getmPrice() != null)
                orderItemBean.setOrderItemPrice(FormatUtils.formatPrice(orderItem.getmPrice()));;

            List<DishOptionItemBean> dishOptionItemBeanList = new ArrayList<DishOptionItemBean>();
            for (OrderItemIngredient ingredient : orderItem.getmOrderItemIngredientSet()) {

                DishOptionItemBean ingredientBean = new DishOptionItemBean();

                if (ingredient.getmName() != null)
                    ingredientBean.setName(ingredient.getmName());
                if (ingredient.getmPrice() != null)
                    ingredientBean.setPrice(FormatUtils.formatPrice(ingredient.getmPrice()));

                dishOptionItemBeanList.add(ingredientBean);
            }
            orderItemBean.setOrderItemOptionItemArray(dishOptionItemBeanList);

            orderItemBeanList.add(orderItemBean);
        }

        this.orderItemArray = orderItemBeanList;

        this.curTimestamp = TimestampUtil.getCurrentTimestamp();
    }

    public String getGetType() {
        return getType;
    }

    public void setGetType(String getType) {
        this.getType = getType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public List<OrderItemBean> getOrderItemArray() {
        return orderItemArray;
    }

    public void setOrderItemArray(List<OrderItemBean> orderItemArray) {
        this.orderItemArray = orderItemArray;
    }

    public String getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(String taxFee) {
        this.taxFee = taxFee;
    }

    public String getTipsFee() {
        return tipsFee;
    }

    public void setTipsFee(String tipsFee) {
        this.tipsFee = tipsFee;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee;
    }

    public String getOrderFormPrice() {
        return orderFormPrice;
    }

    public void setOrderFormPrice(String orderFormPrice) {
        this.orderFormPrice = orderFormPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderFormId() {
        return orderFormId;
    }

    public void setOrderFormId(String orderFormId) {
        this.orderFormId = orderFormId;
    }

    public String getOrderFormCode() {
        return orderFormCode;
    }

    public void setOrderFormCode(String orderFormCode) {
        this.orderFormCode = orderFormCode;
    }

    public String getOrderFormCreateDateTime() {
        return orderFormCreateDateTime;
    }

    public void setOrderFormCreateDateTime(String orderFormCreateDateTime) {
        this.orderFormCreateDateTime = orderFormCreateDateTime;
    }

    public Date getOrderFormCreateTimestamp() {
        return orderFormCreateTimestamp;
    }

    public void setOrderFormCreateTimestamp(Date orderFormCreateTimestamp) {
        this.orderFormCreateTimestamp = orderFormCreateTimestamp;
    }

    public Date getCurTimestamp() {
        return curTimestamp;
    }

    public void setCurTimestamp(Date curTimestamp) {
        this.curTimestamp = curTimestamp;
    }

    public String getOrderFormRestConfirmedDateTime() {
        return orderFormRestConfirmedDateTime;
    }

    public void setOrderFormRestConfirmedDateTime(String orderFormRestConfirmedDateTime) {
        this.orderFormRestConfirmedDateTime = orderFormRestConfirmedDateTime;
    }

    public String getOrderFormCancelDateTime() {
        return orderFormCancelDateTime;
    }

    public void setOrderFormCancelDateTime(String orderFormCancelDateTime) {
        this.orderFormCancelDateTime = orderFormCancelDateTime;
    }

    public String getOrderFormCallDeliveryDateTime() {
        return orderFormCallDeliveryDateTime;
    }

    public void setOrderFormCallDeliveryDateTime(String orderFormCallDeliveryDateTime) {
        this.orderFormCallDeliveryDateTime = orderFormCallDeliveryDateTime;
    }

    public String getOrderFormDriverConfirmedDateTime() {
        return orderFormDriverConfirmedDateTime;
    }

    public void setOrderFormDriverConfirmedDateTime(String orderFormDriverConfirmedDateTime) {
        this.orderFormDriverConfirmedDateTime = orderFormDriverConfirmedDateTime;
    }

    public String getOrderFormArrivedDateTime() {
        return orderFormArrivedDateTime;
    }

    public void setOrderFormArrivedDateTime(String orderFormArrivedDateTime) {
        this.orderFormArrivedDateTime = orderFormArrivedDateTime;
    }

    public Date getOrderFormRestConfirmedTimestamp() {
        return orderFormRestConfirmedTimestamp;
    }

    public void setOrderFormRestConfirmedTimestamp(Date orderFormRestConfirmedTimestamp) {
        this.orderFormRestConfirmedTimestamp = orderFormRestConfirmedTimestamp;
    }

    public Date getOrderFormCancelTimestamp() {
        return orderFormCancelTimestamp;
    }

    public void setOrderFormCancelTimestamp(Date orderFormCancelTimestamp) {
        this.orderFormCancelTimestamp = orderFormCancelTimestamp;
    }

    public Date getOrderFormCallDeliveryTimestamp() {
        return orderFormCallDeliveryTimestamp;
    }

    public void setOrderFormCallDeliveryTimestamp(Date orderFormCallDeliveryTimestamp) {
        this.orderFormCallDeliveryTimestamp = orderFormCallDeliveryTimestamp;
    }

    public Date getOrderFormDriverConfirmedTimestamp() {
        return orderFormDriverConfirmedTimestamp;
    }

    public void setOrderFormDriverConfirmedTimestamp(Date orderFormDriverConfirmedTimestamp) {
        this.orderFormDriverConfirmedTimestamp = orderFormDriverConfirmedTimestamp;
    }

    public Date getOrderFormArrivedTimestamp() {
        return orderFormArrivedTimestamp;
    }

    public void setOrderFormArrivedTimestamp(Date orderFormArrivedTimestamp) {
        this.orderFormArrivedTimestamp = orderFormArrivedTimestamp;
    }

}
