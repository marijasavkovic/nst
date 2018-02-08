package com.master.nst.facade.impl;

import com.master.nst.facade.CourseFacade;
import com.master.nst.model.department.Department;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.service.DepartmentService;
import com.master.nst.service.LevelOfStudiesService;
import com.master.nst.service.TeachingTypeService;
import com.master.nst.sheard.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "course/")
public class CourseFacadeImpl implements CourseFacade {

    private final LevelOfStudiesService levelOfStudiesService;
    private final DepartmentService departmentService;
    private final TeachingTypeService teachingTypeService;

    @Autowired
    public CourseFacadeImpl(
        final LevelOfStudiesService levelOfStudiesService,
        final DepartmentService departmentService,
        final TeachingTypeService teachingTypeService)
    {
        this.levelOfStudiesService = levelOfStudiesService;
        this.departmentService = departmentService;
        this.teachingTypeService = teachingTypeService;
    }

    @Override
    @GetMapping("findAllLevelsOfStudies")
    public Response<List<LevelOfStudies>> findAllLevelOfStudies() {
        return levelOfStudiesService.findAll();
    }

    @Override
    @GetMapping("findAllDepartments")
    public Response<List<Department>> findAllDepartments() {
        return departmentService.findAll();
    }

    @Override
    @GetMapping("findAllTeachingTypes")
    public Response<List<TeachingType>> findAllTeachingTypes() {
        return teachingTypeService.findAll();
    }
}
