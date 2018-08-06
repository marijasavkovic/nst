package com.master.nst.mapper.decorator;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.UserEntity;
import com.master.nst.mapper.UserMapper;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.user.User;
import com.master.nst.model.user.UserCmd;
import com.master.nst.model.user.UserRecord;
import com.master.nst.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<UserRecord> mapToModelList(final List<UserEntity> userEntities) {
        if(userEntities == null){
            return null;
        }
        List<UserRecord> list = new ArrayList<>(userEntities.size());
        for (UserEntity userEntity : userEntities) {
            list.add(mapToRecord(userEntity));
        }

        return list;
    }

    @Override
    public UserRecord mapToRecord(final UserEntity userEntity) {
        UserRecord userRecord = userMapper.mapToRecord(userEntity);

        if(userEntity.getEmployee() != null){
            userRecord.setEmployeeName(userEntity.getEmployee().getName());
            userRecord.setEmployeeSurname(userEntity.getEmployee().getSurname());
        }

        return userRecord;
    }

    @Override
    public UserEntity mapToEntity(final UserCmd model) {
        UserEntity userEntity = userMapper.mapToEntity(model);

        if(model.getEmployee() != null){
            EmployeeEntity employeeEntity = employeeRepository.findById(model.getEmployee()).orElseThrow(RuntimeException::new);
            userEntity.setEmployee(employeeEntity);
        }

        return userEntity;
    }

    @Override
    public void updateEntityFromModel(final UserCmd model, final UserEntity entity) {
        if (model == null) {
            return;
        }
        userMapper.updateEntityFromModel(model, entity);

        if(model.getEmployee() != null){
            EmployeeEntity employeeEntity = employeeRepository.findById(model.getEmployee()).orElseThrow(RuntimeException::new);
            entity.setEmployee(employeeEntity);
        }
    }
}
