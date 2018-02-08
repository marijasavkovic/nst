package com.master.nst.service;

import com.master.nst.model.teachingtype.TeachingType;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface TeachingTypeService {
    Response<List<TeachingType>> findAll();
}
