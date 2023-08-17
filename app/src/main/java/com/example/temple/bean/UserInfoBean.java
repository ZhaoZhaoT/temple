package com.example.temple.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {
    private String avatarUrl;
    private String balance;
    private String dou;
    private String id;
    private String nickName;
    private String phone;
    private String randomId;
    private String sharePath;
    private String withdrawService;
    private String userLevelName;

    public String getWithdrawService() {
        return withdrawService;
    }

    public void setWithdrawService(String withdrawService) {
        this.withdrawService = withdrawService;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDou() {
        return dou;
    }

    public void setDou(String dou) {
        this.dou = dou;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRandomId() {
        return randomId;
    }

    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }

    public String getSharePath() {
        return sharePath;
    }

    public void setSharePath(String sharePath) {
        this.sharePath = sharePath;
    }
}
