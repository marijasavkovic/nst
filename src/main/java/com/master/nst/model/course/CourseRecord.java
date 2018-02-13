package com.master.nst.model.course;

import java.io.Serializable;

public class CourseRecord implements Serializable {

    private Long id;
    private String name;
    private int espb;
    private String levelOfStudiesName;
    private String departmentName;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getEspb() {
        return espb;
    }

    public void setEspb(final int espb) {
        this.espb = espb;
    }

    public String getLevelOfStudiesName() {
        return levelOfStudiesName;
    }

    public void setLevelOfStudiesName(final String levelOfStudiesName) {
        this.levelOfStudiesName = levelOfStudiesName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(final String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("name")
            .append(name).toString();
    }
}
