package com.master.nst.mapper;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.UserEntity;
import com.master.nst.mapper.decorator.CourseMapperDecorator;
import com.master.nst.mapper.decorator.UserMapperDecorator;
import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.user.User;
import com.master.nst.model.user.UserCmd;
import com.master.nst.model.user.UserRecord;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    List<UserRecord> mapToModelList (List<UserEntity> userEntities);

    @Mapping(target = "employeeName", ignore = true)
    @Mapping(target = "employeeSurname", ignore = true)
    UserRecord mapToRecord (UserEntity userEntity);

    User mapToModel (UserEntity userEntity);

    @Mapping(target = "employee", ignore = true)
    UserEntity mapToEntity(UserCmd model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    void updateEntityFromModel(UserCmd userCmd, @MappingTarget UserEntity userEntity);
}
