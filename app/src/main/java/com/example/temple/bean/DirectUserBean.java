package com.example.temple.bean;

public class DirectUserBean extends BaseResponse {

        private String nickName;
        private String avatarUrl;
        private String phone;
        private String createdAt;
        private String sumDouMoney;
        private String userId;
        private String userLevelName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSumDouMoney() {
        return sumDouMoney;
    }

    public void setSumDouMoney(String sumDouMoney) {
        this.sumDouMoney = sumDouMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }
}
