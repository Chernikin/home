package com.aimprosoft.kmb.domain;

import java.util.Objects;

public class Department {

    private long id;
    private String departmentName;
    private String comments;

    public Department() {
    }

    public Department(String departmentName, String comments) {
        this.departmentName = departmentName;
        this.comments = comments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id &&
                Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(comments, that.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentName, comments);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
