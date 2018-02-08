package com.master.nst.service.impl;

import com.master.nst.domain.TeachingTypeEntity;
import com.master.nst.mapper.TeachingTypeMapper;
import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.repository.TeachingTypeRepository;
import com.master.nst.service.TeachingTypeService;
import com.master.nst.sheard.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeachingTypeServiceImpl implements TeachingTypeService {

    private final TeachingTypeRepository teachingTypeRepository;
    private final TeachingTypeMapper teachingTypeMapper;

    @Autowired
    public TeachingTypeServiceImpl(final TeachingTypeRepository teachingTypeRepository,
                                    final TeachingTypeMapper teachingTypeMapper) {
        this.teachingTypeRepository = teachingTypeRepository;
        this.teachingTypeMapper = teachingTypeMapper;
    }

    @Override
    public Response<List<TeachingType>> findAll() {
        return new Response<>(teachingTypeMapper.mapToModelList(teachingTypeRepository.findAll()));
    }
}
