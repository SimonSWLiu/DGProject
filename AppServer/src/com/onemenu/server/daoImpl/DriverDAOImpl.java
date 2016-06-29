package com.onemenu.server.daoImpl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onemenu.server.dao.DriverDAO;
import com.onemenu.server.javabean.condition.DriverQueryCondition;
import com.onemenu.server.model.Driver;
import com.onemenu.server.model.OrderForm;

@Repository
public class DriverDAOImpl extends BaseDAOSupport implements DriverDAO {

    protected HibernateTemplate mHibernateTemplate;

    @Autowired
    public void setmHibernateTemplate(HibernateTemplate mHibernateTemplate) {
        this.mHibernateTemplate = mHibernateTemplate;
    }

    @Override
    @Transactional
    public List<Driver> getDriverListByCondition(DriverQueryCondition condition) {

        DetachedCriteria driverCri = DetachedCriteria.forClass(Driver.class);

        driverCri.addOrder(Order.asc("mCreateTimestamp"));

        driverCri.setFetchMode("mDeliveryTimeSheetSet", FetchMode.SELECT);

        if (condition.getDriverId() != null) {
            driverCri.add(Restrictions.eq("mId", condition.getDriverId()));
        }

        if (condition.getStatus() != null) {
            driverCri.add(Restrictions.eq("mStatus", condition.getStatus()));
        }

        List<Driver> driverList = mHibernateTemplate.findByCriteria(driverCri);

        return driverList;
    }

    @Override
    @Transactional
    public boolean updateDriverStatus(long driverId, Integer status) {

        boolean result = false;

        Driver driver = mHibernateTemplate.get(Driver.class, driverId);

        if (driver != null) {

            driver.setmStatus(status);
            mHibernateTemplate.update(driver);

            result = true;
        }

        return result;
    }

}
