package com.master.nst.service.impl;

import com.master.nst.mapper.LevelOfStudiesMapper;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.repository.LevelOfStudiesRepository;
import com.master.nst.service.LevelOfStudiesService;
import com.master.nst.sheard.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LevelOfStudiesServiceImpl implements LevelOfStudiesService{

    private final LevelOfStudiesRepository levelOfStudiesRepository;
    private final LevelOfStudiesMapper levelOfStudiesMapper;

    @Autowired
    public LevelOfStudiesServiceImpl(final LevelOfStudiesRepository levelOfStudiesRepository,
                                        final LevelOfStudiesMapper levelOfStudiesMapper) {
        this.levelOfStudiesRepository = levelOfStudiesRepository;
        this.levelOfStudiesMapper = levelOfStudiesMapper;
    }

    @Override
    public Response<List<LevelOfStudies>> findAll() {
        return new Response<>(levelOfStudiesMapper.mapToModelList(levelOfStudiesRepository.findAll()));
    }
}
