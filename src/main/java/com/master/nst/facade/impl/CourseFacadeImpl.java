package com.master.nst.facade.impl;

import com.master.nst.facade.CourseFacade;
import com.master.nst.mapper.DepartmentMapper;
import com.master.nst.mapper.LevelOfStudiesMapper;
import com.master.nst.model.department.Department;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.service.DepartmentService;
import com.master.nst.service.LevelOfStudiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CourseFacadeImpl implements CourseFacade {

    private final LevelOfStudiesService levelOfStudiesService;
    private final DepartmentService departmentService;

    private final LevelOfStudiesMapper levelOfStudiesMapper;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public CourseFacadeImpl(
        final LevelOfStudiesService levelOfStudiesService,
        final DepartmentService departmentService,

        final LevelOfStudiesMapper levelOfStudiesMapper,
        final DepartmentMapper departmentMapper)
    {
        this.levelOfStudiesService = levelOfStudiesService;
        this.departmentService = departmentService;

        this.levelOfStudiesMapper = levelOfStudiesMapper;
        this.departmentMapper = departmentMapper;

    }

    @Override
    public List<LevelOfStudies> findAllLevelOfStudies() {
        return levelOfStudiesMapper.mapToModelList(levelOfStudiesService.findAll());
    }

    @Override
    public List<Department> findAllDepartments() {
        return departmentMapper.mapToModelList(departmentService.findAll());
    }
}
