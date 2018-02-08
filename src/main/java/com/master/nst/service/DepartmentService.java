package com.master.nst.service;

import com.master.nst.model.department.Department;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface DepartmentService {
    Response<List<Department>> findAll();
}
