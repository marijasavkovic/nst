package com.master.nst.repository;

import com.master.nst.domain.EmployeeEntity;
import com.master.nst.domain.UserEntity;
import com.master.nst.sheard.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
