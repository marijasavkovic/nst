package com.master.nst.domain;

import com.master.nst.sheard.domain.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @JoinColumn(name = "employee_id")
//    private EmployeeEntity employee;
    @Column(name = "employee_id")
    private Long employee;

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
        setCourse(course, true);
    }

    void setCourse(CourseEntity course, boolean add) {
        this.course = course;
        if (course != null && add) {
            course.addLecturer(this, false);
        }
    }

    public TeachingTypeEntity getTeachingType() {
        return teachingType;
    }

    public void setTeachingType(final TeachingTypeEntity teachingType) {
        this.teachingType = teachingType;
    }

    public Long getEmployee() {
        return employee;
    }

    public void setEmployee(final long employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("employee")
            .append(employee)
            .append("course")
            .append(course.getName())
            .append("teaching type")
            .append(teachingType.getName())
            .toString();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof LecturerEntity) {
            LecturerEntity lecturerEntity = (LecturerEntity) obj;
            if (lecturerEntity.getTeachingType() != null && lecturerEntity.getEmployee() != null) {
                if (lecturerEntity.getTeachingType().equals(teachingType) && lecturerEntity.getEmployee().equals(employee)) {
                    return true;
                }
            }
        }
        return false;
    }
}
