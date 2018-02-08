package com.master.nst.service.impl;

import com.master.nst.domain.DepartmentEntity;
import com.master.nst.mapper.DepartmentMapper;
import com.master.nst.model.department.Department;
import com.master.nst.repository.DepartmentRepository;
import com.master.nst.service.DepartmentService;
import com.master.nst.sheard.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(
        final DepartmentRepository departmentRepository, final DepartmentMapper departmentMapper)
    {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Response<List<Department>> findAll() {

        return new Response<>(departmentMapper.mapToModelList(departmentRepository.findAll()));
    }
}
