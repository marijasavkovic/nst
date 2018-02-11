package com.master.nst.mapper;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    EmployeeEntity mapToEntity(EmployeeCmd model);

    Employee mapToModel(EmployeeEntity entity);

    List<EmployeeRecord> mapToModelList (List<EmployeeEntity> levelOfStudiesEntities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personalIdentificationNumber", ignore = true)
    void updateEntityFromModel(EmployeeCmd model, @MappingTarget EmployeeEntity entity);

}
