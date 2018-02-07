package com.master.nst.repository;

import com.master.nst.domain.DepartmentEntity;
import com.master.nst.sheard.repository.BaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface DepartmentRepository extends BaseRepository<DepartmentEntity, Long> {
}
