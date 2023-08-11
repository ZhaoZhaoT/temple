package com.example.temple.bean.teacher;

import java.util.List;

public class TeacherListBean {
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
        private String teacherImg;
        private String teacherName;
        private String teacherRemark;
        private Double teacherPrice;
        private Integer teacherCount;
        private String teacherAddress;
        private float teacherMark;
        private String teacherPhone;
        private String id;

        public String getTeacherPhone() {
            return teacherPhone;
        }

        public void setTeacherPhone(String teacherPhone) {
            this.teacherPhone = teacherPhone;
        }

        public float getTeacherMark() {
            return teacherMark;
        }

        public void setTeacherMark(float teacherMark) {
            this.teacherMark = teacherMark;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTeacherImg() {
            return teacherImg;
        }

        public void setTeacherImg(String teacherImg) {
            this.teacherImg = teacherImg;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherRemark() {
            return teacherRemark;
        }

        public void setTeacherRemark(String teacherRemark) {
            this.teacherRemark = teacherRemark;
        }

        public Double getTeacherPrice() {
            return teacherPrice;
        }

        public void setTeacherPrice(Double teacherPrice) {
            this.teacherPrice = teacherPrice;
        }

        public Integer getTeacherCount() {
            return teacherCount;
        }

        public void setTeacherCount(Integer teacherCount) {
            this.teacherCount = teacherCount;
        }

        public String getTeacherAddress() {
            return teacherAddress;
        }

        public void setTeacherAddress(String teacherAddress) {
            this.teacherAddress = teacherAddress;
        }
    }

}
