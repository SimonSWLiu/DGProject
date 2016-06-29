package com.onemenu.server.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onemenu.server.constant.ParameterConstant;
import com.onemenu.server.dao.OrderFormDAO;
import com.onemenu.server.javabean.condition.OrderFormQueryCondition;
import com.onemenu.server.model.OrderForm;
import com.onemenu.server.model.OrderItem;
import com.onemenu.server.model.Restaurant;

@Repository
public class OrderFormDAOImpl extends BaseDAOSupport implements OrderFormDAO {

    protected HibernateTemplate mHibernateTemplate;

    @Autowired
    public void setmHibernateTemplate(HibernateTemplate mHibernateTemplate) {
        this.mHibernateTemplate = mHibernateTemplate;
    }

    @Override
    @Transactional
    public List<OrderForm> getOrderFormListByRestaurantId(long restaurantId) {

        List<OrderForm> orderFormList = new ArrayList<OrderForm>();

        Restaurant restaurant = mHibernateTemplate.get(Restaurant.class, restaurantId);

        for (OrderForm orderForm : restaurant.getmOrderFormSet()) {

            Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
            for (OrderItem orderItem : orderForm.getmOrderItemSet()) {

                orderItemSet.add(orderItem);
            }

            orderForm.setmOrderItemSet(orderItemSet);
            orderFormList.add(orderForm);
        }

        return orderFormList;
    }

    public OrderForm findById(long orderFormId) {

        OrderForm orderForm = mHibernateTemplate.get(OrderForm.class, orderFormId);

        Set<OrderItem> orderItemSet = new HashSet<OrderItem>();
        for (OrderItem orderItem : orderForm.getmOrderItemSet()) {

            orderItemSet.add(orderItem);
        }

        orderForm.setmOrderItemSet(orderItemSet);

        return orderForm;
    }

    @Override
    @Transactional
    public List<OrderForm> getOrderFormListByCondition(OrderFormQueryCondition condition) {

        DetachedCriteria orderFormCri = DetachedCriteria.forClass(OrderForm.class);
        orderFormCri.addOrder(Order.desc("mCreateTimestamp"));
        orderFormCri.setFetchMode("mOrderItemSet", FetchMode.SELECT);

        if (condition.getRestaurantId() != null) {
            orderFormCri.add(Restrictions.eq("mRestaurant.mId", condition.getRestaurantId()));
        }
        
        if (!condition.getStatus().equals("")) {
            orderFormCri.add(Restrictions.eq("mStatus", condition.getStatus()));
        }
        if (condition.getFromTimestamp() != null) {
            orderFormCri.add(Restrictions.ge("mCreateTimestamp", condition.getFromTimestamp()));
        }
        if (condition.getToTimestamp() != null) {
            orderFormCri.add(Restrictions.le("mCreateTimestamp", condition.getToTimestamp()));
        }
        if (condition.getIsOneMenu() != null) {
            orderFormCri.add(Restrictions.eq("mIsOneMenu", condition.getIsOneMenu()));
        }

        List<OrderForm> orderFormList = mHibernateTemplate.findByCriteria(orderFormCri);

        return orderFormList;
    }

    final String payment_pie_sql = " SELECT "
            + " CASE WHEN t.payment_type = :creditCardType THEN :creditCardTypeDesc "
            + " WHEN t.payment_type = :paypalType THEN :paypalTypeDesc "
            + " WHEN t.payment_type = :cashType THEN :cashTypeDesc "
            + " ELSE :otherDesc END AS label," + " sum(of.subtotal_fee) AS data"
            + " FROM order_form of" + " LEFT JOIN trade t" + " ON of.trade_id = t.id"
            + " WHERE of.restaurant_id = :restaurantId and of.status = :status" + " ?1"
            + " GROUP BY t.payment_type";

    @Override
    @Transactional
    public List getPaymentPieByCondition(OrderFormQueryCondition condition) {

        Session session = mHibernateTemplate.getSessionFactory().getCurrentSession();

        StringBuilder sqlBuilder = new StringBuilder(" ");
        if (condition.getFromTimestamp() != null)
            sqlBuilder.append(" AND of.create_timestamp > :fromTimestamp");
        if (condition.getToTimestamp() != null)
            sqlBuilder.append(" AND of.create_timestamp < :toTimestamp");
        String newSql = payment_pie_sql.replace("?1", sqlBuilder.toString());

        Query query = session.createSQLQuery(newSql);

        if (condition.getFromTimestamp() != null)
            query.setParameter("fromTimestamp", condition.getFromTimestamp());
        if (condition.getToTimestamp() != null)
            query.setParameter("toTimestamp", condition.getToTimestamp());
        query.setParameter("creditCardType", ParameterConstant.TRADE_PAYMENT_TYPE_CREDIT_CARD);
        query.setParameter("creditCardTypeDesc",
                ParameterConstant.DESC_TRADE_PAYMENT_TYPE_CREDIT_CARD);
        query.setParameter("paypalType", ParameterConstant.TRADE_PAYMENT_TYPE_PAYPAL);
        query.setParameter("paypalTypeDesc", ParameterConstant.DESC_TRADE_PAYMENT_TYPE_PAYPAL);
        query.setParameter("cashType", ParameterConstant.TRADE_PAYMENT_TYPE_CASH);
        query.setParameter("cashTypeDesc", ParameterConstant.DESC_TRADE_PAYMENT_TYPE_CASH);
        query.setParameter("otherDesc", ParameterConstant.DESC_TRADE_PAYMENT_TYPE_OTHERS);
        query.setParameter("restaurantId", condition.getRestaurantId());
        query.setParameter("status", condition.getStatus());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List list = query.list();

        return list;
    }

    final String axe_sql = " SELECT" + " UNIX_TIMESTAMP(left( create_timestamp , 10 ))*1000 AS ms,"
            + " sum(subtotal_fee) AS amount"
            + " FROM order_form WHERE restaurant_id = :restaurantId and status = :status" + " ?1"
            + " GROUP BY ms DESC";

    @Override
    @Transactional
    public List getAxeByCondition(OrderFormQueryCondition condition) {

        Session session = mHibernateTemplate.getSessionFactory().getCurrentSession();

        StringBuilder sqlBuilder = new StringBuilder(" ");
        if (condition.getFromTimestamp() != null)
            sqlBuilder.append(" AND create_timestamp > :fromTimestamp");
        if (condition.getToTimestamp() != null)
            sqlBuilder.append(" AND create_timestamp < :toTimestamp");
        String newSql = axe_sql.replace("?1", sqlBuilder.toString());

        Query query = session.createSQLQuery(newSql);

        if (condition.getFromTimestamp() != null)
            query.setParameter("fromTimestamp", condition.getFromTimestamp());
        if (condition.getToTimestamp() != null)
            query.setParameter("toTimestamp", condition.getToTimestamp());
        query.setParameter("restaurantId", condition.getRestaurantId());
        query.setParameter("status", condition.getStatus());

        List list = query.list();

        return list;
    }

    final String line_sql = " SELECT"
            + " UNIX_TIMESTAMP(left( create_timestamp , 10 ))*1000 AS ms," + " count(*) AS count"
            + " FROM order_form WHERE restaurant_id = :restaurantId and status = :status" + " ?1"
            + " GROUP BY ms DESC";

    @Override
    @Transactional
    public List getlineByCondition(OrderFormQueryCondition condition) {

        Session session = mHibernateTemplate.getSessionFactory().getCurrentSession();

        StringBuilder sqlBuilder = new StringBuilder(" ");
        if (condition.getFromTimestamp() != null)
            sqlBuilder.append(" AND create_timestamp > :fromTimestamp");
        if (condition.getToTimestamp() != null)
            sqlBuilder.append(" AND create_timestamp < :toTimestamp");
        String newSql = line_sql.replace("?1", sqlBuilder.toString());

        Query query = session.createSQLQuery(newSql);

        if (condition.getFromTimestamp() != null)
            query.setParameter("fromTimestamp", condition.getFromTimestamp());
        if (condition.getToTimestamp() != null)
            query.setParameter("toTimestamp", condition.getToTimestamp());
        query.setParameter("restaurantId", condition.getRestaurantId());
        query.setParameter("status", condition.getStatus());

        List list = query.list();

        return list;
    }

    @Override
    @Transactional
    public boolean updateOrderFormStatus(long orderFormId, String status) {

        boolean result = false;

        OrderForm orderForm = mHibernateTemplate.get(OrderForm.class, orderFormId);

        if (orderForm != null) {

            orderForm.setmStatus(status);
            mHibernateTemplate.update(orderForm);

            result = true;
        }

        return result;
    }

}
