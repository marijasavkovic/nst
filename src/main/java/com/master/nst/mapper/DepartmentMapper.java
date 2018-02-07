package com.master.nst.mapper;

import com.master.nst.domain.DepartmentEntity;
import com.master.nst.model.department.Department;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface DepartmentMapper {

    List<Department> mapToModelList (List<DepartmentEntity> departmentEntities);

}
