package com.master.nst.service;

import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface LevelOfStudiesService {
    Response<List<LevelOfStudies>> findAll();
}
