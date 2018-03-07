package com.master.nst.mapper.decorator;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.DepartmentEntity;
import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.LecturerEntity;
import com.master.nst.domain.LevelOfStudiesEntity;
import com.master.nst.domain.TeachingTypeEntity;
import com.master.nst.domain.ThematicUnitEntity;
import com.master.nst.mapper.CourseMapper;
import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.model.lecturer.Lecturer;
import com.master.nst.model.lecturer.LecturerCmd;
import com.master.nst.model.thematicunit.ThematicUnit;
import com.master.nst.model.thematicunit.ThematicUnitCmd;
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
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeachingTypeRepository teachingTypeRepository;

    @Override
    public List<CourseRecord> mapToModelList(final List<CourseEntity> courseEntities) {
        if (courseEntities == null) {
            return null;
        }

        List<CourseRecord> list = new ArrayList<>(courseEntities.size());
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
        mapThematicUnits(model.getThematicUnitsList(), courseEntity);
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
        if (model == null) {
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

        updateLecturerList(model.getLecturerList(), entity);
        mapThematicUnits(model.getThematicUnitsList(), entity);

    }

    private void mapLecturerList(List<LecturerCmd> lecturers, CourseEntity entity) {
        if (entity.getLecturerList() != null) {
            entity.getLecturerList().clear();
        }
        if (lecturers != null && !lecturers.isEmpty()) {
            for (LecturerCmd lecturerCmd : lecturers) {
                LecturerEntity lecturerEntity = lecturerCmdToLecturerEntity(lecturerCmd);
                lecturerEntity.setCourse(entity);
                entity.addLecturer(lecturerEntity);
            }
        }
    }

    private void updateLecturerList(List<LecturerCmd> lecturers, CourseEntity entity) {
        List<LecturerEntity> lecturerEntities = new ArrayList<>();
        for (LecturerCmd lecturerCmd : lecturers) {
            lecturerEntities.add(lecturerCmdToLecturerEntity(lecturerCmd));
        }
        entity.getLecturerList().removeIf((LecturerEntity lecturerEntity) -> {
            return !lecturerEntities.contains(lecturerEntity);
        });
        for (LecturerEntity lecturerEntity : lecturerEntities) {
            entity.addLecturer(lecturerEntity);
        }
    }

    private void mapThematicUnits(List<ThematicUnitCmd> list, CourseEntity entity) {
        if (entity.getThematicUnitsList() != null) {
            entity.getThematicUnitsList().clear();
        }
        if (list != null && !list.isEmpty()) {
            for (ThematicUnitCmd thematicUnitCmd : list) {
                if(entity.getThematicUnitsList()!=null && !entity.getThematicUnitsList().stream()
                    .map(ThematicUnitEntity::getSerialNumber).anyMatch(thematicUnitCmd.getSerialNumber()::equalsIgnoreCase)) {
                    entity.getThematicUnitsList().add(cmdToEntityThematicUnit(list, thematicUnitCmd, entity));
                }
            }
        }
    }

    private ThematicUnitEntity cmdToEntityThematicUnit(
        final List<ThematicUnitCmd> list,
        final ThematicUnitCmd thematicUnitCmd,
        CourseEntity entity)
    {

        ThematicUnitEntity thematicUnitEntity = thematicUnitCmdToThematicUnitEntity(thematicUnitCmd);
        thematicUnitEntity.setCourse(entity);
        if (thematicUnitCmd.getParentThematicUnitSerialNumber() != null && !thematicUnitCmd
            .getParentThematicUnitSerialNumber()
            .isEmpty()) {

            ThematicUnitEntity parent = findParentTemathicUnit(
                list, entity, thematicUnitCmd.getParentThematicUnitSerialNumber());
            thematicUnitEntity.setParentThematicUnit(parent);
        }
        return thematicUnitEntity;
    }

    private ThematicUnitEntity findParentTemathicUnit(
        final List<ThematicUnitCmd> list,
        final CourseEntity entity,
        final String parentThematicUnitSerialNumber)
    {

        for (ThematicUnitEntity thematicUnitEntity : entity.getThematicUnitsList()) {
            if (thematicUnitEntity.getSerialNumber().equalsIgnoreCase(parentThematicUnitSerialNumber)) {
                return thematicUnitEntity;
            }
        }

        for (ThematicUnitCmd thematicUnitCmd : list) {
            if (thematicUnitCmd.getSerialNumber().equalsIgnoreCase(parentThematicUnitSerialNumber)) {
                ThematicUnitEntity parent = cmdToEntityThematicUnit(list, thematicUnitCmd, entity);
                entity.getThematicUnitsList().add(parent);
                return parent;
            }
        }
        return null;

    }

    @Override
    public CourseCmd courseToCourseCmd(final Course course) {
        CourseCmd courseCmd = courseMapper.courseToCourseCmd(course);

        if(course.getDepartment()!=null)courseCmd.setDepartmentId(course.getDepartment().getId());
        if(course.getLevelOfStudies()!=null) courseCmd.setLevelOfStudiesId(course.getLevelOfStudies().getId());
        courseCmd.setLecturerList(new ArrayList<>());
        courseCmd.setThematicUnitsList(new ArrayList<>());
        for (Lecturer lecturer : course.getLecturerList()){
            courseCmd.getLecturerList().add(lecturerToLecturerCmd(lecturer));
        }
        for (ThematicUnit thematicUnit : course.getThematicUnitsList()){
            System.out.println(thematicUnit.getParentThematicUnit());
            courseCmd.getThematicUnitsList().add(thematicUnitToThematicUnitCmd(thematicUnit));
        }
        return courseCmd;
    }

    private LecturerCmd lecturerToLecturerCmd(final Lecturer lecturer) {
        LecturerCmd lecturerCmd = new LecturerCmd();
        lecturerCmd.setId(lecturer.getId());
        if(lecturer.getEmployee()!=null)lecturerCmd.setEmployeeId(lecturer.getEmployee().getId());
        if(lecturer.getTeachingType()!=null)lecturerCmd.setTeachingTypeId(lecturer.getTeachingType().getId());
        return lecturerCmd;
    }

    private ThematicUnitCmd thematicUnitToThematicUnitCmd(final ThematicUnit thematicUnit) {
        ThematicUnitCmd thematicUnitCmd = new ThematicUnitCmd();
        thematicUnitCmd.setId(thematicUnit.getId());
        thematicUnitCmd.setName(thematicUnit.getName());
        thematicUnitCmd.setDescription(thematicUnit.getDescription());
        thematicUnitCmd.setSerialNumber(thematicUnit.getSerialNumber());
        thematicUnitCmd.setParentThematicUnitSerialNumber(thematicUnit.getParentThematicUnit()!=null ? thematicUnit.getParentThematicUnit().getSerialNumber():null);
        return thematicUnitCmd;
    }
}
