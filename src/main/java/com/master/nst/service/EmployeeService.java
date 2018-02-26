package com.master.nst.service;

import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface EmployeeService {

    Response<List<EmployeeRecord>> findAll();

    Response<Employee> findById(Long id);

    Response<Employee> add(EmployeeCmd employeeCmd);

    Response<Employee> edit(Long employeeId, EmployeeCmd employeeCmd);

    Response<?> delete(Long employeeId);
}
