package com.master.nst.mapper;

import com.master.nst.domain.LecturerEntity;
import com.master.nst.model.lecturer.Lecturer;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface LecturerMapper {

    List<Lecturer> mapToModelList (List<LecturerEntity> courseEntities);

    Lecturer mapToModel(LecturerEntity entity);

}
