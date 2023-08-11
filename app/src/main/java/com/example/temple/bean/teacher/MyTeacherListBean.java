package com.example.temple.bean.teacher;

import java.util.List;

public class MyTeacherListBean {
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
        private String id;
        private long endAt;
        private long startAt;
        private TeacherListBean.ContentBean teacherVO;
        private String name;
        private String status;
        private String phone;
        private Double price;
        private String remark;
        private String score;
        private String reviewContent;

        public String getReviewContent() {
            return reviewContent;
        }

        public void setReviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
        }

        //
        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        //        public Float getScore() {
//            return score;
//        }
//
//        public void setScore(Float score) {
//            this.score = score;
//        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getEndAt() {
            return endAt;
        }

        public void setEndAt(long endAt) {
            this.endAt = endAt;
        }

        public long getStartAt() {
            return startAt;
        }

        public void setStartAt(long startAt) {
            this.startAt = startAt;
        }

        public TeacherListBean.ContentBean getTeacherVO() {
            return teacherVO;
        }

        public void setTeacherVO(TeacherListBean.ContentBean teacherVO) {
            this.teacherVO = teacherVO;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }

}
