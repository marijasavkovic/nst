package com.master.nst.mapper;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.LecturerEntity;
import com.master.nst.mapper.decorator.CourseMapperDecorator;
import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.lecturer.Lecturer;
import com.master.nst.model.lecturer.LecturerCmd;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
@DecoratedWith(CourseMapperDecorator.class)
public interface CourseMapper {

    List<CourseRecord> mapToModelList (List<CourseEntity> courseEntities);

    CourseRecord mapToRecord (CourseEntity courseEntity);

    Course mapToModel (CourseEntity courseEntity);

    Lecturer lecturerEntityToLecturer (LecturerEntity lecturerEntity);

    CourseEntity mapToEntity(CourseCmd model);

    LecturerEntity lecturerCmdToLecturerEntity (LecturerCmd lecturerCmd);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lecturerList", ignore = true)
    void updateEntityFromModel(CourseCmd courseCmd, @MappingTarget CourseEntity courseEntity);
}
