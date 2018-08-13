package com.master.nst.mapper.decorator;

import com.master.nst.domain.LecturerEntity;
import com.master.nst.elasticsearch.service.EmployeeElasticService;
import com.master.nst.mapper.EmployeeMapper;
import com.master.nst.mapper.LecturerMapper;
import com.master.nst.model.lecturer.Lecturer;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class LecturerMapperDecorator implements LecturerMapper {

    @Autowired
    private LecturerMapper lecturerMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeElasticService elasticService;

    @Override
    public Lecturer mapToModel(final LecturerEntity entity) {
        Lecturer lecturer = lecturerMapper.mapToModel(entity);

        if(entity.getEmployee()!=null){
            lecturer.setEmployee(employeeMapper.mapToModel(elasticService.findById(entity.getEmployee())));
        }

        return lecturer;
    }
}
