package com.aimprosoft.kmb.domain;

import javax.persistence.*;
import java.util.Objects;


@javax.persistence.Entity
@Table(name = "departments")
public class Department implements Entity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Department_name")
    private String departmentName;
    private String comments;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department(String departmentName, String comments) {
        this.departmentName = departmentName;
        this.comments = comments;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
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
        Department department = (Department) o;
        return Objects.equals(id, department.id) &&
                Objects.equals(departmentName, department.departmentName) &&
                Objects.equals(comments, department.comments);
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
