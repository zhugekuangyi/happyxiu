package com.mingsheng.model;

import java.sql.Timestamp;

public class MobileSale {

    private String id;

    private String mobileType;

    private String mobileName;

    private String img;

    private Timestamp ctime;

    private Double price;

    private String mobileMemory;

    private String mobileColour;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileType() {
        return mobileType;
    }

    public void setMobileType(String mobileType) {
        this.mobileType = mobileType;
    }

    public String getMobileName() {
        return mobileName;
    }

    public void setMobileName(String mobileName) {
        this.mobileName = mobileName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Timestamp getCtime() {
        return ctime;
    }

    public void setCtime(Timestamp ctime) {
        this.ctime = ctime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMobileMemory() {
        return mobileMemory;
    }

    public void setMobileMemory(String mobileMemory) {
        this.mobileMemory = mobileMemory;
    }

    public String getMobileColour() {
        return mobileColour;
    }

    public void setMobileColour(String mobileColour) {
        this.mobileColour = mobileColour;
    }
}
