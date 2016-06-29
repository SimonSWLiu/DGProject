package com.onemenu.server.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.dao.OrderFormDAO;
import com.onemenu.server.javabean.bean.OrderFormBean;
import com.onemenu.server.javabean.bean.StatementBean;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.model.OrderForm;
import com.onemenu.server.service.OrderFormService;
import com.onemenu.server.util.FormatUtils;

@Service
public class OrderFormServiceImpl extends AbstractServiceImpl implements OrderFormService {

    private OrderFormDAO mOrderFormDAO;

    @Autowired
    public void setmOrderFormDAO(OrderFormDAO mOrderFormDAO) {
        this.mOrderFormDAO = mOrderFormDAO;
    }

    @Override
    public List<OrderForm> listOrderFormByRestaurantId(long restaurantId) {

        List<OrderForm> orderFormList = mOrderFormDAO.getOrderFormListByRestaurantId(restaurantId);

        if (orderFormList.size() != 0) {
            return orderFormList;
        } else if (orderFormList.size() == 0) {
            return null;
        }

        return null;
    }

    @Override
    @Transactional
    public boolean confirmOrderFormByRestaurant(long orderFromId) {

        return mOrderFormDAO.updateOrderFormStatus(orderFromId,
                ParameterConstant.ORDER_FORM_STATUS_RESTAURANT_CONFIRMED);
    }

    @Override
    @Transactional
    public boolean finishOrderForm(long orderFromId) {

        return mOrderFormDAO.updateOrderFormStatus(orderFromId,
                ParameterConstant.ORDER_FORM_STATUS_FINISHED);
    }

    @Override
    @Transactional
    public boolean cancelOrderForm(long orderFromId) {

        return mOrderFormDAO.updateOrderFormStatus(orderFromId,
                ParameterConstant.ORDER_FORM_STATUS_CANCEL);
    }

    @Override
    public OrderFormBean getOrderFormByOrderFormId(long orderFormId) {

        OrderForm orderForm = mOrderFormDAO.findById(orderFormId);
        OrderFormBean orderFormBean = new OrderFormBean(orderForm);

        return orderFormBean;
    }

    @Override
    public List<OrderFormBean> getOrderFormListByCondition(OrderFormQueryCondition condition) {

        List<OrderForm> orderFormList = mOrderFormDAO.getOrderFormListByCondition(condition);

        List<OrderFormBean> orderFormBeanList = new ArrayList<OrderFormBean>();

        for (OrderForm orderForm : orderFormList) {
            OrderFormBean orderFormBean = new OrderFormBean(orderForm);
            orderFormBeanList.add(orderFormBean);
        }

        return orderFormBeanList;
    }

    @Override
    public List listStatementByCondition(OrderFormQueryCondition condition) {

        List statementList = new ArrayList();

        List<OrderForm> orderFormList = mOrderFormDAO.getOrderFormListByCondition(condition);

        StatementBean creditCardStatement = new StatementBean();
        creditCardStatement.setPaymentType(ParameterConstant.DESC_TRADE_PAYMENT_TYPE_CREDIT_CARD);
        BigDecimal creditCardNetSales = new BigDecimal(0.00);
        BigDecimal creditCardTax = new BigDecimal(0.00);
        BigDecimal creditCardSalesTax = new BigDecimal(0.00);
        BigDecimal creditCardDeliveryFee = new BigDecimal(0.00);
        BigDecimal creditCardTips = new BigDecimal(0.00);
        BigDecimal creditCardPaymentGatewayFees = new BigDecimal(0.00);
        BigDecimal creditCardNetIncome = new BigDecimal(0.00);

        StatementBean paypalStatement = new StatementBean();
        paypalStatement.setPaymentType(ParameterConstant.DESC_TRADE_PAYMENT_TYPE_PAYPAL);
        BigDecimal paypalNetSales = new BigDecimal(0.00);
        BigDecimal paypalTax = new BigDecimal(0.00);
        BigDecimal paypalSalesTax = new BigDecimal(0.00);
        BigDecimal paypalDeliveryFee = new BigDecimal(0.00);
        BigDecimal paypalTips = new BigDecimal(0.00);
        BigDecimal paypalPaymentGatewayFees = new BigDecimal(0.00);
        BigDecimal paypalNetIncome = new BigDecimal(0.00);

        StatementBean cashStatement = new StatementBean();
        cashStatement.setPaymentType(ParameterConstant.DESC_TRADE_PAYMENT_TYPE_CASH);
        BigDecimal cashNetSales = new BigDecimal(0.00);
        BigDecimal cashTax = new BigDecimal(0.00);
        BigDecimal cashSalesTax = new BigDecimal(0.00);
        BigDecimal cashDeliveryFee = new BigDecimal(0.00);
        BigDecimal cashTips = new BigDecimal(0.00);
        BigDecimal cashPaymentGatewayFees = new BigDecimal(0.00);
        BigDecimal cashNetIncome = new BigDecimal(0.00);

        for (OrderForm orderForm : orderFormList) {

            BigDecimal saleFee = new BigDecimal(0.00);
            if (orderForm.getmSubtotalFee() != null)
                saleFee = orderForm.getmSubtotalFee();
            BigDecimal taxFee = new BigDecimal(0.00);
            if (orderForm.getmTaxFee() != null)
                taxFee = orderForm.getmTaxFee();
            BigDecimal salesTaxFee = new BigDecimal(0.00);;
            if (orderForm.getmTotalFee() != null)
                salesTaxFee = orderForm.getmTotalFee();
            BigDecimal deliveryFee = new BigDecimal(0.00);
            if (orderForm.getmDeliveryFee() != null)
                deliveryFee = orderForm.getmDeliveryFee();
            BigDecimal tipsFee = new BigDecimal(0.00);
            if (orderForm.getmTipsFee().signum() != -1) {
                tipsFee = orderForm.getmTipsFee();
            }

            if (orderForm.getmTrade().getmPaymentType()
                    .equals(ParameterConstant.TRADE_PAYMENT_TYPE_CREDIT_CARD)) {

                creditCardNetSales = creditCardNetSales.add(saleFee);
                creditCardTax = creditCardTax.add(taxFee);
                creditCardSalesTax = creditCardSalesTax.add(salesTaxFee); // Sale+Tax
                creditCardDeliveryFee = creditCardDeliveryFee.add(deliveryFee);
                creditCardTips = creditCardTips.add(tipsFee);

                // paymentGatewayFees=(Sales+Tax+Delivery Fee+Tips)*0.29+0.30
                BigDecimal paymentGatewayFees =
                        saleFee.add(taxFee).add(deliveryFee).add(tipsFee)
                                .multiply(ParameterConstant.ORDER_FORM_PAYMENT_GATWAY_CHARGE)
                                .add(ParameterConstant.ORDER_FORM_PAYMENT_GATWAY_CHARGE_PER_ORDER);
                // netIncome=(Sales+Tax+Delivery Fees+Tips)-Payment Gateway Fees
                BigDecimal netIncome =
                        saleFee.add(taxFee).add(deliveryFee).add(tipsFee)
                                .subtract(paymentGatewayFees);
                creditCardPaymentGatewayFees = creditCardPaymentGatewayFees.add(paymentGatewayFees);
                creditCardNetIncome = creditCardNetIncome.add(netIncome);
            } else if (orderForm.getmTrade().getmPaymentType()
                    .equals(ParameterConstant.TRADE_PAYMENT_TYPE_PAYPAL)) {

                paypalNetSales = paypalNetSales.add(saleFee);
                paypalTax = paypalTax.add(taxFee);
                paypalSalesTax = paypalSalesTax.add(salesTaxFee); // Sale+Tax
                paypalDeliveryFee = paypalDeliveryFee.add(deliveryFee);
                paypalTips = paypalTips.add(tipsFee);

                // paymentGatewayFees=(Sales+Tax+Delivery Fee+Tips)*0.29+0.30
                BigDecimal paymentGatewayFees =
                        saleFee.add(taxFee).add(deliveryFee).add(tipsFee)
                                .multiply(ParameterConstant.ORDER_FORM_PAYMENT_GATWAY_CHARGE)
                                .add(ParameterConstant.ORDER_FORM_PAYMENT_GATWAY_CHARGE_PER_ORDER);
                // netIncome=(Sales+Tax+Delivery Fees+Tips)-Payment Gateway Fees
                BigDecimal netIncome =
                        saleFee.add(taxFee).add(deliveryFee).add(tipsFee)
                                .subtract(paymentGatewayFees);
                paypalPaymentGatewayFees = paypalPaymentGatewayFees.add(paymentGatewayFees);
                paypalNetIncome = paypalNetIncome.add(netIncome);
            } else if (orderForm.getmTrade().getmPaymentType()
                    .equals(ParameterConstant.TRADE_PAYMENT_TYPE_CASH)) {

                cashNetSales = cashNetSales.add(saleFee);
                cashTax = cashTax.add(taxFee);
                cashSalesTax = cashSalesTax.add(salesTaxFee); // Sale+Tax
                cashDeliveryFee = cashDeliveryFee.add(deliveryFee);
                cashTips = cashTips.add(tipsFee);

                // cash have no paymentGatewayFees
                // netIncome=(Sales+Tax+Delivery Fees+Tips)
                BigDecimal netIncome = saleFee.add(taxFee).add(deliveryFee).add(tipsFee);
                // No PaymentGatewayFees in cash
                cashNetIncome = cashNetIncome.add(netIncome);
            }

        }

        creditCardStatement.setNetSales(FormatUtils.formatPrice(creditCardNetSales));
        creditCardStatement.setTax(FormatUtils.formatPrice(creditCardTax));
        creditCardStatement.setSalesTax(FormatUtils.formatPrice(creditCardSalesTax));
        creditCardStatement.setDeliveryFee(FormatUtils.formatPrice(creditCardDeliveryFee));
        creditCardStatement.setTips(FormatUtils.formatPrice(creditCardTips));
        creditCardStatement.setPaymentGatewayFees(FormatUtils
                .formatPrice(creditCardPaymentGatewayFees));
        creditCardStatement.setNetIncome(FormatUtils.formatPrice(creditCardNetIncome));
        statementList.add(creditCardStatement);

        paypalStatement.setNetSales(FormatUtils.formatPrice(paypalNetSales));
        paypalStatement.setTax(FormatUtils.formatPrice(paypalTax));
        paypalStatement.setSalesTax(FormatUtils.formatPrice(paypalSalesTax));
        paypalStatement.setDeliveryFee(FormatUtils.formatPrice(paypalDeliveryFee));
        paypalStatement.setTips(FormatUtils.formatPrice(paypalTips));
        paypalStatement.setPaymentGatewayFees(FormatUtils.formatPrice(paypalPaymentGatewayFees));
        paypalStatement.setNetIncome(FormatUtils.formatPrice(paypalNetIncome));
        statementList.add(paypalStatement);

        cashStatement.setNetSales(FormatUtils.formatPrice(cashNetSales));
        cashStatement.setTax(FormatUtils.formatPrice(cashTax));
        cashStatement.setSalesTax(FormatUtils.formatPrice(cashSalesTax));
        cashStatement.setDeliveryFee(FormatUtils.formatPrice(cashDeliveryFee));
        cashStatement.setTips(FormatUtils.formatPrice(cashTips));
        cashStatement.setPaymentGatewayFees(FormatUtils.formatPrice(cashPaymentGatewayFees));
        cashStatement.setNetIncome(FormatUtils.formatPrice(cashNetIncome));
        statementList.add(cashStatement);

        return statementList;
    }

    @Override
    public List listPaymentPieByCondition(OrderFormQueryCondition condition) {

        List list = mOrderFormDAO.getPaymentPieByCondition(condition);

        return list;
    }

    @Override
    public List listAxeByCondition(OrderFormQueryCondition condition) {

        List list = mOrderFormDAO.getAxeByCondition(condition);

        return list;
    }

    @Override
    public List listLineByCondition(OrderFormQueryCondition condition) {

        List list = mOrderFormDAO.getlineByCondition(condition);

        return list;
    }

    @Override
    public List<OrderForm> listOrderFormByCondition(OrderFormQueryCondition condition) {

        List<OrderForm> orderFormList = mOrderFormDAO.getOrderFormListByCondition(condition);

        if (orderFormList.size() != 0) {
            return orderFormList;
        } else if (orderFormList.size() == 0) {
            return null;
        }

        return null;
    }
}
