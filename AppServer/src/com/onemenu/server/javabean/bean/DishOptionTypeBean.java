package com.onemenu.server.javabean.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.onemenu.server.model.DishOptionType;

public class DishOptionTypeBean {

    public String id;
    public String name;
    public String isMandatory;
    public String isSingle;

    public DishOptionTypeBean() {

    }

    public DishOptionTypeBean(DishOptionType dishOptionType) {

        this.id = String.valueOf(dishOptionType.getmId());
        this.name = dishOptionType.getmName();
        this.isMandatory = dishOptionType.getmIsMandatory();
        this.isSingle = dishOptionType.getmIsSingle();
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

    public String getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(String isMandatory) {
        this.isMandatory = isMandatory;
    }

    public String getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(String isSingle) {
        this.isSingle = isSingle;
    }

    public static List<DishOptionTypeBean> toDishOptionTypeBeanList(
            Set<DishOptionType> dishOptionTypeSet) {

        List<DishOptionTypeBean> dishOptionTypeBeanList = new ArrayList<DishOptionTypeBean>();

        for (DishOptionType dishOptionType : dishOptionTypeSet) {

            DishOptionTypeBean dishOptionTypeBean = new DishOptionTypeBean(dishOptionType);
            dishOptionTypeBeanList.add(dishOptionTypeBean);
        }

        return dishOptionTypeBeanList;
    }

}
