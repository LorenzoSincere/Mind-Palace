package com.lorenzo.mind_palace.entity;

import java.util.Date;

public class Category {
    private Byte categoryId;

    private String name;

    private Date lastUpdate;

    public Byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Byte categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", categoryId=" + categoryId +
                ", name=" + name +
                ", lastUpdate=" + lastUpdate +
                "]";
    }
}
