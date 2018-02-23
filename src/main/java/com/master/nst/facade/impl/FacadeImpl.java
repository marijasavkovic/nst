package com.master.nst.facade.impl;

import com.master.nst.facade.Facade;
import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.department.Department;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.service.CourseService;
import com.master.nst.service.DepartmentService;
import com.master.nst.service.EmployeeService;
import com.master.nst.service.LevelOfStudiesService;
import com.master.nst.service.TeachingTypeService;
import com.master.nst.sheard.errors.Error;
import com.master.nst.sheard.exception.ValidationException;
import com.master.nst.sheard.response.Response;
import com.master.nst.sheard.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "application/")
public class FacadeImpl implements Facade {

    private final LevelOfStudiesService levelOfStudiesService;
    private final DepartmentService departmentService;
    private final TeachingTypeService teachingTypeService;
    private final EmployeeService employeeService;
    private final CourseService courseService;

    @Autowired
    public FacadeImpl(
        final LevelOfStudiesService levelOfStudiesService,
        final DepartmentService departmentService,
        final TeachingTypeService teachingTypeService,
        final EmployeeService employeeService,
        final CourseService courseService
        )
    {
        this.levelOfStudiesService = levelOfStudiesService;
        this.departmentService = departmentService;
        this.teachingTypeService = teachingTypeService;
        this.employeeService = employeeService;
        this.courseService = courseService;
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
    public Response<Employee> addEmployee(@Valid @RequestBody EmployeeCmd employeeCmd) {
        return employeeService.add(employeeCmd);
    }

    @Override
    @PutMapping("employee/{employeeId}")
    public Response<Employee> editEmployee(@PathVariable Long employeeId,@Valid @RequestBody EmployeeCmd employeeCmd) {
        return employeeService.edit(employeeId, employeeCmd);
    }

    @Override
    @GetMapping("course/findAllCourses")
    public Response<List<CourseRecord>> findAllCourses() {
        return courseService.findAll();
    }

    @Override
    @GetMapping("course/{courseId}")
    public Response<Course> findCourseById(@PathVariable Long courseId) {
        return courseService.findById(courseId);
    }

    @Override
    @PostMapping("course")
    public Response<Course> addCourse(@Valid @RequestBody CourseCmd courseCmd) {
        return courseService.add(courseCmd);
    }

    @Override
    @PutMapping("course/{courseId}")
    public Response<Course> editCourse(@PathVariable Long courseId, @Valid @RequestBody CourseCmd courseCmd) {
        return courseService.edit(courseId, courseCmd);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<Error> errors = ex
            .getBindingResult()
            .getFieldErrors()
            .stream()
            .map(fe -> new Error(fe.getDefaultMessage()))
            .collect(Collectors.toList());

        return new Response<>(com.master.nst.sheard.response.ResponseStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler(ValidationException.class)
    public Response<?> handleValidationException (ValidationException ex) {

        return new Response<>(ResponseStatus.INTERNAL_SERVER_ERROR, ex.getErrors());
    }

}
