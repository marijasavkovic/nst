package com.master.nst.facade;

import com.master.nst.model.department.Department;
import com.master.nst.model.levelofstudies.LevelOfStudies;

import java.util.List;

public interface CourseFacade {

    List<LevelOfStudies> findAllLevelOfStudies();

    List<Department> findAllDepartments();
}
