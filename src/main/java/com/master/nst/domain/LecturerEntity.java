package com.master.nst.domain;

import com.master.nst.sheard.domain.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lecturer")
public class LecturerEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "teaching_type_id")
    private TeachingTypeEntity teachingType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(final CourseEntity course) {
        this.course = course;
    }

    public TeachingTypeEntity getTeachingType() {
        return teachingType;
    }

    public void setTeachingType(final TeachingTypeEntity teachingType) {
        this.teachingType = teachingType;
    }

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(final EmployeeEntity employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("employee")
            .append(employee.getName())
            .append("course")
            .append(course.getName())
            .append("teaching type")
            .append(teachingType.getName()).toString();
    }
}
