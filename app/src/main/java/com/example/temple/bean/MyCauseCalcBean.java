package com.example.temple.bean;

import java.util.List;

public class MyCauseCalcBean {
    private List<ContentBean> listData;
    private String queryMonthMoney;
    private String queryData;

    public List<ContentBean> getListData() {
        return listData;
    }

    public void setListData(List<ContentBean> listData) {
        this.listData = listData;
    }

    public String getQueryMonthMoney() {
        return queryMonthMoney;
    }

    public void setQueryMonthMoney(String queryMonthMoney) {
        this.queryMonthMoney = queryMonthMoney;
    }

    public String getQueryData() {
        return queryData;
    }

    public void setQueryData(String queryData) {
        this.queryData = queryData;
    }

    public static class ContentBean {
        private String toDayMoney;
        private String toDayDate;

        public String getToDayMoney() {
            return toDayMoney;
        }

        public void setToDayMoney(String toDayMoney) {
            this.toDayMoney = toDayMoney;
        }

        public String getToDayDate() {
            return toDayDate;
        }

        public void setToDayDate(String toDayDate) {
            this.toDayDate = toDayDate;
        }
    }
}
