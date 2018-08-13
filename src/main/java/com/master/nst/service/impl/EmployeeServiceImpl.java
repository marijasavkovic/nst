package com.master.nst.service.impl;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.elasticsearch.index.EmployeeIndexer;
import com.master.nst.elasticsearch.service.EmployeeElasticService;
import com.master.nst.mapper.EmployeeMapper;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.repository.EmployeeRepository;
import com.master.nst.service.EmployeeService;
import com.master.nst.sheard.response.Response;
import com.master.nst.sheard.response.ResponseStatus;
import com.master.nst.validator.employee.EmployeeValidator;
import org.elasticsearch.action.search.SearchResponse;
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
    private final EmployeeValidator.Add employeeValidatorAdd;
    private final EmployeeValidator.Edit employeeValidatorEdit;
    private final EmployeeValidator.Delete employeeValidatorDelete;

    @Autowired
    EmployeeElasticService elasticService;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository,
                               final EmployeeMapper employeeMapper,
                               final EmployeeValidator.Add employeeValidatorAdd,
                               final EmployeeValidator.Edit employeeValidatorEdit,
                               final EmployeeValidator.Delete employeeValidatorDelete
    ) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.employeeValidatorAdd = employeeValidatorAdd;
        this.employeeValidatorEdit = employeeValidatorEdit;
        this.employeeValidatorDelete = employeeValidatorDelete;
    }

    @Override
    public Response<List<EmployeeRecord>> findAll() {
        return new Response<>(employeeMapper.mapToModelList(elasticService.findAll()));
    }

    @Override
    public Response<Employee> findById(final Long id) {
        try {
            EmployeeEntity employeeEntity = elasticService.findById(id);
            return new Response<>(employeeMapper.mapToModel(employeeEntity));
        } catch (Exception e) {
            return new Response<>(ResponseStatus.NOT_FOUND);
        }
    }

    @Override
    public Response<Employee> add(final EmployeeCmd employeeCmd) {
        try {
            employeeValidatorAdd.validate(employeeCmd);
            return new Response<>(employeeMapper.mapToModel(elasticService.save(employeeMapper.mapToEntity(employeeCmd))));
        } catch (Exception e) {
            return new Response<>(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response<Employee> edit(final Long employeeId, final EmployeeCmd employeeCmd) {
        employeeValidatorEdit.validate(employeeId, employeeCmd);
        try {
            return new Response<>(employeeMapper.mapToModel(elasticService.update(employeeMapper.mapToEntity(employeeCmd))));
        } catch (Exception e) {
            return new Response<>(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response<?> delete(final Long employeeId) {
        employeeValidatorDelete.validate(employeeId);
        elasticService.delete(employeeId);
        return new Response<>(ResponseStatus.OK, "Employee is deleted successfully!");
    }

}
