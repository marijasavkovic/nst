package com.master.nst.service.impl;

import com.master.nst.domain.CourseEntity;
import com.master.nst.domain.UserEntity;
import com.master.nst.mapper.UserMapper;
import com.master.nst.model.course.Course;
import com.master.nst.model.user.User;
import com.master.nst.model.user.UserCmd;
import com.master.nst.model.user.UserRecord;
import com.master.nst.repository.UserRepository;
import com.master.nst.service.UserService;
import com.master.nst.sheard.response.Response;
import com.master.nst.sheard.response.ResponseStatus;
import com.master.nst.validator.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator.Add userValidatorAdd;
    @Autowired
    private UserValidator.Edit userValidatorEdit;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Response<List<UserRecord>> findAll() {
        return new Response<>(userMapper.mapToModelList(userRepository.findAll()));
    }

    @Override
    public Response<User> findById(final Long id) {
        Optional<User> user = userRepository.findById(id).map(userMapper::mapToModel);
        return user.map(Response::new).orElse(new Response<>(ResponseStatus.NOT_FOUND));
    }

    @Override
    public Response<User> add(final UserCmd userCmd) {
        userValidatorAdd.validate(userCmd);

        UserEntity userEntity = userMapper.mapToEntity(userCmd);
        User user = userMapper.mapToModel(userRepository.save(userEntity));

        return new Response<>(user);
    }

    @Override
    public Response<User> edit(final Long userId, final UserCmd userCmd) {
        UserEntity userEntity = userRepository.findById(userId)
            .orElseThrow(RuntimeException::new);

        userMapper.updateEntityFromModel(userCmd, userEntity);
        User user = userMapper.mapToModel(userRepository.save(userEntity));
        return new Response<>(user);
    }

    @Override
    public Response<?> delete(final Long userId) {
        userRepository.delete(userId);
        return new Response<>(ResponseStatus.OK, "User is deleted successfully!");
    }
}
