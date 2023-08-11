package com.example.temple.bean;

import java.util.Objects;

public class TypeBean {

    private String createdAt;
    private int createdBy;
    private int id;
    private int sort;
    private String typeName;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeBean typeBean = (TypeBean) o;
        return id == typeBean.id && Objects.equals(typeName, typeBean.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeName);
    }
}