package com.master.nst.mapper.decorator;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.DepartmentEntity;
import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.LecturerEntity;
import com.master.nst.domain.LevelOfStudiesEntity;
import com.master.nst.domain.TeachingTypeEntity;
import com.master.nst.mapper.CourseMapper;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.lecturer.LecturerCmd;
import com.master.nst.repository.CourseRepository;
import com.master.nst.repository.DepartmentRepository;
import com.master.nst.repository.EmployeeRepository;
import com.master.nst.repository.LevelOfStudiesRepository;
import com.master.nst.repository.TeachingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class CourseMapperDecorator implements CourseMapper {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LevelOfStudiesRepository levelOfStudiesRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeachingTypeRepository teachingTypeRepository;

    @Override
    public List<CourseRecord> mapToModelList(final List<CourseEntity> courseEntities) {
        if (courseEntities == null) {
            return null;
        }

        List<CourseRecord> list = new ArrayList<CourseRecord>(courseEntities.size());
        for (CourseEntity courseEntity : courseEntities) {
            list.add(mapToRecord(courseEntity));
        }

        return list;
    }

    @Override
    public CourseRecord mapToRecord(final CourseEntity courseEntity) {
        CourseRecord courseRecord = courseMapper.mapToRecord(courseEntity);

        if (courseEntity.getDepartment() != null) {
            courseRecord.setDepartmentName(courseEntity.getDepartment().getName());
        }
        if (courseEntity.getLecturerList() != null) {
            courseRecord.setLevelOfStudiesName(courseEntity.getLevelOfStudies().getName());
        }

        return courseRecord;
    }

    @Override
    public CourseEntity mapToEntity(final CourseCmd model) {
        CourseEntity courseEntity = courseMapper.mapToEntity(model);

        if (model.getDepartmentId() != null) {
            DepartmentEntity departmentEntity = departmentRepository
                .findById(model.getDepartmentId())
                .orElseThrow(RuntimeException::new);
            courseEntity.setDepartment(departmentEntity);
        }

        if (model.getLevelOfStudiesId() != null) {
            LevelOfStudiesEntity levelOfStudiesEntity = levelOfStudiesRepository
                .findById(model.getLevelOfStudiesId())
                .orElseThrow(RuntimeException::new);
            courseEntity.setLevelOfStudies(levelOfStudiesEntity);
        }

        mapLecturerList(model.getLecturerList(), courseEntity);
        return courseEntity;
    }

    @Override
    public LecturerEntity lecturerCmdToLecturerEntity(final LecturerCmd lecturerCmd) {
        LecturerEntity lecturerEntity = courseMapper.lecturerCmdToLecturerEntity(lecturerCmd);

        if (lecturerCmd.getEmployeeId() != null) {
            EmployeeEntity employeeEntity = employeeRepository
                .findById(lecturerCmd.getEmployeeId())
                .orElseThrow(RuntimeException::new);
            lecturerEntity.setEmployee(employeeEntity);
        }

        if (lecturerCmd.getTeachingTypeId() != null) {
            TeachingTypeEntity teachingTypeEntity = teachingTypeRepository
                .findById(lecturerCmd.getTeachingTypeId())
                .orElseThrow(RuntimeException::new);
            lecturerEntity.setTeachingType(teachingTypeEntity);
        }

        return lecturerEntity;
    }

    @Override
    public void updateEntityFromModel(final CourseCmd model, final CourseEntity entity) {
        if ( model == null ) {
            return;
        }
        courseMapper.updateEntityFromModel(model, entity);

        if (model.getDepartmentId() != null) {
            DepartmentEntity departmentEntity = departmentRepository
                .findById(model.getDepartmentId())
                .orElseThrow(RuntimeException::new);
            entity.setDepartment(departmentEntity);
        }

        if (model.getLevelOfStudiesId() != null) {
            LevelOfStudiesEntity levelOfStudiesEntity = levelOfStudiesRepository
                .findById(model.getLevelOfStudiesId())
                .orElseThrow(RuntimeException::new);
            entity.setLevelOfStudies(levelOfStudiesEntity);
        }

        mapLecturerList(model.getLecturerList(), entity);

    }

    private void mapLecturerList (List<LecturerCmd> lecturers, CourseEntity entity){
        if (lecturers != null && !lecturers.isEmpty()) {
            List<LecturerEntity> list = new ArrayList<LecturerEntity>(lecturers.size());
            for (LecturerCmd lecturerCmd : lecturers) {
                LecturerEntity lecturerEntity = lecturerCmdToLecturerEntity(lecturerCmd);
                lecturerEntity.setCourse(entity);
                list.add(lecturerEntity);
            }
            entity.setLecturerList(list);
        }
    }
}
