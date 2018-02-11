package com.master.nst.facade;

import com.master.nst.model.department.Department;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface Facade {

    Response<List<LevelOfStudies>> findAllLevelOfStudies();

    Response<List<Department>> findAllDepartments();

    Response<List<TeachingType>> findAllTeachingTypes();

    Response<List<EmployeeRecord>> findAllEmployees();

    Response<Employee> findEmployeeById(Long id);

    Response<Employee> addEmployee(EmployeeCmd employeeCmd);

    Response<Employee> editEmployee(Long employeeId, EmployeeCmd employeeCmd);
}
