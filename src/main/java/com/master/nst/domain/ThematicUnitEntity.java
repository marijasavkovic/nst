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
@Table(name = "thematic_unit")
public class ThematicUnitEntity extends BaseEntity<Long>{

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "tematic_unit_parent_id")
    private ThematicUnitEntity parentThematicUnit;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(final CourseEntity course) {
        this.course = course;
    }

    public ThematicUnitEntity getParentThematicUnit() {
        return parentThematicUnit;
    }

    public void setParentThematicUnit(final ThematicUnitEntity parentThematicUnit) {
        this.parentThematicUnit = parentThematicUnit;
    }

    @Override
    public String toString() {
        return "ThematicUnitEntity{" + "name='" + name + '\'' + ", serialNumber='" + serialNumber + '\''
            + ", description='" + description + '\'' + ", course=" + course + ", parentThematicUnit="
            + parentThematicUnit + '}';
    }
}
