package com.example.temple.bean;

import java.io.Serializable;

public class KangNingDetailsBean implements Serializable {
    private String title;
    private String descriptionOne;
    private String descriptionTwo;
    private String type;
    private int godCount;
    private int lampCount;
    private String godPrice;

    public String getGodPrice() {
        return godPrice;
    }

    public void setGodPrice(String godPrice) {
        this.godPrice = godPrice;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionOne() {
        return descriptionOne;
    }

    public void setDescriptionOne(String descriptionOne) {
        this.descriptionOne = descriptionOne;
    }

    public String getDescriptionTwo() {
        return descriptionTwo;
    }

    public void setDescriptionTwo(String descriptionTwo) {
        this.descriptionTwo = descriptionTwo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGodCount() {
        return godCount;
    }

    public void setGodCount(int godCount) {
        this.godCount = godCount;
    }

    public int getLampCount() {
        return lampCount;
    }

    public void setLampCount(int lampCount) {
        this.lampCount = lampCount;
    }
}
