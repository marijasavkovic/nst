package com.master.nst.model.lecturer;

import com.master.nst.sheard.errors.ErrorMessages;
import com.master.nst.sheard.validation.ValidationGroups;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class LecturerCmd implements Serializable{

    @NotNull(message = ErrorMessages.ID_NULL, groups = ValidationGroups.Edit.class)
    private Long id;

    @NotNull(message = ErrorMessages.TEACHING_TYPE_NULL)
    private Long teachingTypeId;

    @NotNull(message = ErrorMessages.COURSE_NULL)
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
