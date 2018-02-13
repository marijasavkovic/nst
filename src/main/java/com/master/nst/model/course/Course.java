package com.master.nst.model.course;

import com.master.nst.model.department.Department;
import com.master.nst.model.lecturer.Lecturer;
import com.master.nst.model.levelofstudies.LevelOfStudies;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

    private Long id;
    private String name;
    private int espb;
    private String goal;
    private String methodOfEvaluation;
    private LevelOfStudies levelOfStudies;
    private Department department;
    private List<Lecturer> lecturerList;

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

    public String getGoal() {
        return goal;
    }

    public void setGoal(final String goal) {
        this.goal = goal;
    }

    public String getMethodOfEvaluation() {
        return methodOfEvaluation;
    }

    public void setMethodOfEvaluation(final String methodOfEvaluation) {
        this.methodOfEvaluation = methodOfEvaluation;
    }

    public LevelOfStudies getLevelOfStudies() {
        return levelOfStudies;
    }

    public void setLevelOfStudies(final LevelOfStudies levelOfStudies) {
        this.levelOfStudies = levelOfStudies;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(final Department department) {
        this.department = department;
    }

    public List<Lecturer> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(final List<Lecturer> lecturerList) {
        this.lecturerList = lecturerList;
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
