package com.master.nst.service;

import com.master.nst.model.course.Course;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.model.course.CourseRecord;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface CourseService {

    Response<List<CourseRecord>> findAll();

    Response<Course> findById(Long id);

    Response<Course> add(CourseCmd courseCmd);

    Response<Course> edit(Long courseId, CourseCmd courseCmd);

}
