package com.master.nst.mapper;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.LecturerEntity;
import com.master.nst.domain.ThematicUnitEntity;
import com.master.nst.mapper.decorator.CourseMapperDecorator;
import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.lecturer.Lecturer;
import com.master.nst.model.lecturer.LecturerCmd;
import com.master.nst.model.thematicunit.ThematicUnit;
import com.master.nst.model.thematicunit.ThematicUnitCmd;
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

    @Mapping(target = "levelOfStudiesName", ignore = true)
    @Mapping(target = "departmentName", ignore = true)
    CourseRecord mapToRecord (CourseEntity courseEntity);

    Course mapToModel (CourseEntity courseEntity);

    Lecturer lecturerEntityToLecturer (LecturerEntity lecturerEntity);

    ThematicUnit thematicUnitEntityToThematicUnit (ThematicUnitEntity thematicUnitEntity);

    @Mapping(target = "levelOfStudies", ignore = true)
    @Mapping(target = "department", ignore = true)
    CourseEntity mapToEntity(CourseCmd model);

    @Mapping(target = "course", ignore = true)
    @Mapping(target = "teachingType", ignore = true)
    @Mapping(target = "employee", ignore = true)
    LecturerEntity lecturerCmdToLecturerEntity (LecturerCmd lecturerCmd);

    @Mapping(target = "parentThematicUnit", ignore = true)
    @Mapping(target = "course", ignore = true)
    ThematicUnitEntity thematicUnitCmdToThematicUnitEntity (ThematicUnitCmd thematicUnitCmd);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lecturerList", ignore = true)
    @Mapping(target = "thematicUnitsList", ignore = true)
    @Mapping(target = "levelOfStudies", ignore = true)
    @Mapping(target = "department", ignore = true)
    void updateEntityFromModel(CourseCmd courseCmd, @MappingTarget CourseEntity courseEntity);

    @Mapping(target = "lecturerList", ignore = true)
    @Mapping(target = "thematicUnitsList", ignore = true)
    @Mapping(target = "levelOfStudiesId", ignore = true)
    @Mapping(target = "departmentId", ignore = true)
    CourseCmd courseToCourseCmd (Course course);
}
