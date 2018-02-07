package com.master.nst.service;

import com.master.nst.domain.DepartmentEntity;

import java.util.List;

public interface DepartmentService {
    List<DepartmentEntity> findAll();
}
