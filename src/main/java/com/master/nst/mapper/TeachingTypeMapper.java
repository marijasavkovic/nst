package com.master.nst.mapper;

import com.master.nst.domain.TeachingTypeEntity;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface TeachingTypeMapper {

    List<TeachingType> mapToModelList(List<TeachingTypeEntity> teachingTypeEntities);
}
