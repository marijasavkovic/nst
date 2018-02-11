package com.master.nst.facade.impl;

import com.master.nst.facade.Facade;
import com.master.nst.model.department.Department;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.service.DepartmentService;
import com.master.nst.service.EmployeeService;
import com.master.nst.service.LevelOfStudiesService;
import com.master.nst.service.TeachingTypeService;
import com.master.nst.sheard.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "application/")
public class FacadeImpl implements Facade {

    private final LevelOfStudiesService levelOfStudiesService;
    private final DepartmentService departmentService;
    private final TeachingTypeService teachingTypeService;
    private final EmployeeService employeeService;

    @Autowired
    public FacadeImpl(
        final LevelOfStudiesService levelOfStudiesService,
        final DepartmentService departmentService,
        final TeachingTypeService teachingTypeService,
        final EmployeeService employeeService
        )
    {
        this.levelOfStudiesService = levelOfStudiesService;
        this.departmentService = departmentService;
        this.teachingTypeService = teachingTypeService;
        this.employeeService = employeeService;
    }

    @Override
    @GetMapping("levelOfStudies/findAllLevelsOfStudies")
    public Response<List<LevelOfStudies>> findAllLevelOfStudies() {
        return levelOfStudiesService.findAll();
    }

    @Override
    @GetMapping("department/findAllDepartments")
    public Response<List<Department>> findAllDepartments() {
        return departmentService.findAll();
    }

    @Override
    @GetMapping("teachingType/findAllTeachingTypes")
    public Response<List<TeachingType>> findAllTeachingTypes() {
        return teachingTypeService.findAll();
    }

    @Override
    @GetMapping("employee/findAllEmployees")
    public Response<List<EmployeeRecord>> findAllEmployees() {
        return employeeService.findAll();
    }

    @Override
    @GetMapping("employee/{employeeId}")
    public Response<Employee> findEmployeeById(@PathVariable Long employeeId) {
        return employeeService.findById(employeeId);
    }

    @Override
    @PostMapping("employee")
    public Response<Employee> addEmployee(@RequestBody EmployeeCmd employeeCmd) {
        return employeeService.add(employeeCmd);
    }

    @Override
    @PutMapping("employee/{employeeId}")
    public Response<Employee> editEmployee(@PathVariable Long employeeId, @RequestBody EmployeeCmd employeeCmd) {
        return employeeService.edit(employeeId, employeeCmd);
    }
}
