package com.master.nst.repository;

import com.master.nst.domain.LecturerEntity;
import com.master.nst.sheard.repository.BaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface LecturerRepository extends BaseRepository<LecturerEntity, Long> {

    void deleteByCourse_Id(Long courseId);

}
