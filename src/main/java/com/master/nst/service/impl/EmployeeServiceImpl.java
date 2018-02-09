package com.master.nst.service.impl;

import com.master.nst.mapper.EmployeeMapper;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.repository.EmployeeRepository;
import com.master.nst.service.EmployeeService;
import com.master.nst.sheard.response.Response;
import com.master.nst.sheard.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository, final EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Response<List<EmployeeRecord>> findAll() {
        return new Response<>(employeeMapper.mapToModelList(employeeRepository.findAll()));
    }

    @Override
    public Response<Employee> findById(final Long id) {
        Optional<Employee> employee = employeeRepository.findById(id).map(employeeMapper::mapToModel);
        return employee.map(Response::new).orElse(new Response<>(ResponseStatus.NOT_FOUND));
    }

    @Override
    public Response<Employee> add(final EmployeeCmd employeeCmd) {
        return null;
    }

    @Override
    public Response<Employee> edit(final EmployeeCmd employeeCmd) {
        return null;
    }
}
