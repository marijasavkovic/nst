package com.master.nst.mapper;

import com.master.nst.domain.LevelOfStudiesEntity;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.sheard.constants.Constants;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = Constants.MAPPER_COMPONENT_MODEL)
public interface LevelOfStudiesMapper {

    List<LevelOfStudies> mapToModelList (List<LevelOfStudiesEntity> levelOfStudiesEntities);

}
