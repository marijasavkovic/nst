package com.master.nst.endpoint;

import com.master.nst.facade.CourseFacade;
import com.master.nst.model.department.Department;
import com.master.nst.model.levelofstudies.LevelOfStudies;
import com.master.nst.sheard.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "course/")
public class CourseEndpoint {

    private final CourseFacade courseFacade;

    @Autowired
    public CourseEndpoint(final CourseFacade courseFacade) {
        this.courseFacade = courseFacade;
    }

    @GetMapping("findAllLevelsOfStudies")
    public Response<List<LevelOfStudies>> findAllLevelOfStudies(){
        return new Response<>(courseFacade.findAllLevelOfStudies());
    }

    @GetMapping("findAllDepartments")
    public Response<List<Department>> findAllDepartments(){
        return new Response<>(courseFacade.findAllDepartments());
    }
}
