package com.master.nst.domain;

import com.master.nst.sheard.domain.BaseEntity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "espb", nullable = false)
    private int espb;

    @Column(name = "goal", nullable = false)
    private String goal;

    @Column(name = "method_of_evaluation", nullable = false)
    private String methodOfEvaluation;

    @ManyToOne
    @JoinColumn(name = "level_of_studies_id")
    private LevelOfStudiesEntity levelOfStudies;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "course", orphanRemoval = true)
    private List<LecturerEntity> lecturerList;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "course", orphanRemoval = true)
    private List<ThematicUnitEntity> thematicUnitsList;

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

    public LevelOfStudiesEntity getLevelOfStudies() {
        return levelOfStudies;
    }

    public void setLevelOfStudies(final LevelOfStudiesEntity levelOfStudies) {
        this.levelOfStudies = levelOfStudies;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(final DepartmentEntity department) {
        this.department = department;
    }

    public List<LecturerEntity> getLecturerList() {
        return lecturerList;
    }

    public void setLecturerList(final List<LecturerEntity> lecturerList) {
        this.lecturerList = lecturerList;
    }

    public List<ThematicUnitEntity> getThematicUnitsList() {
        return thematicUnitsList;
    }

    public void setThematicUnitsList(final List<ThematicUnitEntity> thematicUnitsList) {
        this.thematicUnitsList = thematicUnitsList;
    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getSimpleName())
            .append("id")
            .append(id)
            .append("name")
            .append(name)
            .append("department")
            .append(department)
            .toString();
    }
}
