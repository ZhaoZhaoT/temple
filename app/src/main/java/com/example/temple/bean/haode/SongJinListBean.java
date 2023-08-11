package com.example.temple.bean.haode;

import java.util.List;

public class SongJinListBean {
    private List<ContentBean> content;
    private int size;
    private int totalElements;
    private int totalPages;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public static class ContentBean {
        private String lrcUrl;
        private String mpUrl;
        private String title;
        private String id;
        private String videoTime;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getLrcUrl() {
            return lrcUrl;
        }

        public void setLrcUrl(String lrcUrl) {
            this.lrcUrl = lrcUrl;
        }

        public String getMpUrl() {
            return mpUrl;
        }

        public void setMpUrl(String mpUrl) {
            this.mpUrl = mpUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideoTime() {
            return videoTime;
        }

        public void setVideoTime(String videoTime) {
            this.videoTime = videoTime;
        }
    }

}
