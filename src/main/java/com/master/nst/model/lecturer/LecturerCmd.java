package com.master.nst.model.lecturer;

import java.io.Serializable;

public class LecturerCmd implements Serializable{

    private Long id;
    private Long teachingTypeId;
    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTeachingTypeId() {
        return teachingTypeId;
    }

    public void setTeachingTypeId(final Long teachingTypeId) {
        this.teachingTypeId = teachingTypeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(final Long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("employee id")
            .append(employeeId)
            .append("teaching type id")
            .append(teachingTypeId).toString();
    }
}
