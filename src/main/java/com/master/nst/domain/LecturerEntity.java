package com.master.nst.domain;

import com.master.nst.sheard.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lecturer")
public class LecturerEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "teaching_type_id", referencedColumnName = "id")
    private TeachingTypeEntity teachingType;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
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
