package com.onemenu.server.javabean.bean;

import com.onemenu.server.model.DishCategory;


public class DishCategoryBean {

    public String id;
    public String name;
    public String createDate;
    public String sequence;

    
    public DishCategoryBean() {

    }
    
    public DishCategoryBean(DishCategory dishCategory) {

        this.id = String.valueOf(dishCategory.getmId());

        if (dishCategory.getmName() != null)
            this.name = dishCategory.getmName();

        if (dishCategory.getmCreateTimestamp() != null)
            this.createDate = dishCategory.getmCreateTimestamp().toString();

        if (dishCategory.getmSequence() != null)
            this.sequence = String.valueOf(dishCategory.getmSequence());

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

}
