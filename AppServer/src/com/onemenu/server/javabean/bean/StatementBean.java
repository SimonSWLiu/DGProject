package com.onemenu.server.javabean.bean;


public class StatementBean {

    public String paymentType;
    public String netSales;
    public String tax;
    public String salesTax;
    public String deliveryFee;
    public String tips;
    public String paymentGatewayFees;
    public String netIncome;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getNetSales() {
        return netSales;
    }

    public void setNetSales(String netSales) {
        this.netSales = netSales;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(String salesTax) {
        this.salesTax = salesTax;
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getPaymentGatewayFees() {
        return paymentGatewayFees;
    }

    public void setPaymentGatewayFees(String paymentGatewayFees) {
        this.paymentGatewayFees = paymentGatewayFees;
    }

    public String getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(String netIncome) {
        this.netIncome = netIncome;
    }

}
