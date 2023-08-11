package com.example.temple.bean.haode;

import java.util.List;

public class RankListBean {
    private RankBean myRanking;

    private List<RankBean> ranking;

    public RankBean getMyRanking() {
        return myRanking;
    }

    public void setMyRanking(RankBean myRanking) {
        this.myRanking = myRanking;
    }

    public List<RankBean> getRanking() {
        return ranking;
    }

    public void setRanking(List<RankBean> ranking) {
        this.ranking = ranking;
    }

    public static class RankBean {
        private String avatar_url;
        private int counts;
        private String contentLength;
        private String nick_name;
        private String user_id;

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public String getContentLength() {
            return contentLength;
        }

        public void setContentLength(String contentLength) {
            this.contentLength = contentLength;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }

}
