package com.master.nst.validator.course;

import com.master.nst.domain.CourseEntity;
import com.master.nst.model.course.CourseCmd;
import com.master.nst.repository.CourseRepository;
import com.master.nst.sheard.errors.Error;
import com.master.nst.sheard.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CourseValidator{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseValidator(final CourseRepository courseRepository) {
        this.courseRepository= courseRepository;
    }

    @Component
    public class Add {

        public void validate(CourseCmd courseCmd) {

            List<Error> errors = new ArrayList<>();

            Error error = checkNameUnique(courseCmd.getName());

            if (error!=null) {
                errors.add(new Error("Course with this name already exists"));
            }

            if(!errors.isEmpty()){
                throw new ValidationException(errors);
            }

        }
    }

    private Error checkNameUnique (String name){
        final Optional<CourseEntity> courseEntity = courseRepository.findByName(
            name);

        if (courseEntity.isPresent()) {
            return  new Error("Course with this name already exists");
        }
        return null;
    }

    @Component
    public class Edit {

        public void validate(Long courseId, CourseCmd courseCmd) {

        }
    }

    @Component
    public class Delete {

        public void validate(Long courseId) {

        }

    }


}
