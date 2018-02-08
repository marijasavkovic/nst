package com.master.nst.facade;

import com.master.nst.model.department.Department;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface CourseFacade {

    Response<List<LevelOfStudies>> findAllLevelOfStudies();

    Response<List<Department>> findAllDepartments();

    Response<List<TeachingType>> findAllTeachingTypes();
}
