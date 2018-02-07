package com.master.nst.service.impl;

import com.master.nst.domain.LevelOfStudiesEntity;
import com.master.nst.repository.LevelOfStudiesRepository;
import com.master.nst.service.LevelOfStudiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LevelOfStudiesServiceImpl implements LevelOfStudiesService{

    private LevelOfStudiesRepository levelOfStudiesRepository;

    @Autowired
    public LevelOfStudiesServiceImpl(final LevelOfStudiesRepository levelOfStudiesRepository) {
        this.levelOfStudiesRepository = levelOfStudiesRepository;
    }

    @Override
    public List<LevelOfStudiesEntity> findAll() {
        return levelOfStudiesRepository.findAll();
    }
}
