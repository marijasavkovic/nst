package com.master.nst.model.course;

import com.master.nst.model.lecturer.LecturerCmd;
import com.master.nst.sheard.errors.ErrorMessages;
import com.master.nst.sheard.validation.ValidationGroups;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class CourseCmd implements Serializable{

    @NotNull(message = ErrorMessages.ID_NULL, groups = ValidationGroups.Edit.class)
    private Long id;

    @NotEmpty(message = ErrorMessages.NAME_EMPTY)
    private String name;

    @NotNull(message = ErrorMessages.ESPB_NULL)
    private int espb;

    @NotEmpty(message = ErrorMessages.GOAL_EMPTY)
    private String goal;

    @NotEmpty(message = ErrorMessages.METHOD_OF_EVALUATION_EMPTY)
    private String methodOfEvaluation;

    @NotNull(message = ErrorMessages.LEVEL_OF_STUDIES_NULL)
    private Long levelOfStudiesId;

    @NotNull(message = ErrorMessages.DEPARTMENT_NULL)
    private Long departmentId;

    @NotEmpty(message = ErrorMessages.LECTURER_LIST_EMPTY)
    private List<LecturerCmd> lecturerList;

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

    public Long getLevelOfStudiesId() {
        return levelOfStudiesId;
    }

    public void setLevelOfStudiesId(final Long levelOfStudiesId) {
        this.levelOfStudiesId = levelOfStudiesId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(final Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<LecturerCmd> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(final List<LecturerCmd> lecturerList) {
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
