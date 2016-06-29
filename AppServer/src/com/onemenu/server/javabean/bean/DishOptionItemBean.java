package com.onemenu.server.javabean.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.onemenu.server.model.DishOptionItem;

public class DishOptionItemBean {

    public String id;
    public String name;
    public String price;

    public DishOptionItemBean() {

    }

    public DishOptionItemBean(DishOptionItem dishOptionItem) {

        this.id = String.valueOf(dishOptionItem.getmId());
        this.name = dishOptionItem.getmName();
        this.price = String.valueOf(dishOptionItem.getmPrice());
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


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public static List<DishOptionItemBean> toDishOptionItemBeanList(
            Set<DishOptionItem> dishOptionItemSet) {

        List<DishOptionItemBean> dishOptionItemBeanList = new ArrayList<DishOptionItemBean>();

        for (DishOptionItem dishOptionItem : dishOptionItemSet) {

            DishOptionItemBean dishOptionItemBean = new DishOptionItemBean(dishOptionItem);
            dishOptionItemBeanList.add(dishOptionItemBean);
        }

        return dishOptionItemBeanList;
    }

}
