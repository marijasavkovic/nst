package com.master.nst.service;

import com.master.nst.model.employee.Employee;
import com.master.nst.model.employee.EmployeeCmd;
import com.master.nst.model.employee.EmployeeRecord;
import com.master.nst.model.user.User;
import com.master.nst.model.user.UserCmd;
import com.master.nst.model.user.UserRecord;
import com.master.nst.sheard.response.Response;

import java.util.List;

public interface UserService {

    Response<List<UserRecord>> findAll();

    Response<User> findById(Long id);

    Response<User> add(UserCmd userCmd);

    Response<User> edit(Long userId, UserCmd userCmd);

    Response<?> delete(Long userId);

}
