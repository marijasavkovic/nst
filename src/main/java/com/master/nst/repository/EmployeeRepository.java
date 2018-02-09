package com.master.nst.repository;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.sheard.repository.BaseRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface EmployeeRepository extends BaseRepository<EmployeeEntity, Long>{

    Optional<EmployeeEntity> findByPersonalIdentificationNumber(String personalIdentificationNumber);

}
