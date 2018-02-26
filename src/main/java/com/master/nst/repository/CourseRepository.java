package com.master.nst.repository;

import com.master.nst.domain.CourseEntity;
import com.master.nst.sheard.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends BaseRepository<CourseEntity, Long> {

    @Query(
        "select c from CourseEntity c left join c.lecturerList l left join l.employee e where e.id = ?1 ")
    List<CourseEntity> findByEmployeeId (Long employeeId);

    Optional<CourseEntity> findByName(String name);
}
