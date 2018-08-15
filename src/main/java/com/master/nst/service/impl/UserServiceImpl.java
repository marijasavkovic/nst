package com.master.nst.service.impl;

import com.master.nst.domain.UserEntity;
import com.master.nst.elasticsearch.service.UserElasticService;
import com.master.nst.mapper.UserMapper;
import com.master.nst.model.user.User;
import com.master.nst.model.user.UserCmd;
import com.master.nst.model.user.UserRecord;
import com.master.nst.service.UserService;
import com.master.nst.sheard.response.Response;
import com.master.nst.sheard.response.ResponseStatus;
import com.master.nst.validator.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserElasticService userElasticService;
    @Autowired
    private UserValidator.Add userValidatorAdd;
    @Autowired
    private UserValidator.Edit userValidatorEdit;
    @Autowired
    private UserValidator.Delete userValidatorDelete;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Response<List<UserRecord>> findAll() {
        return new Response<>(userMapper.mapToModelList(userElasticService.findAll()));
    }

    @Override
    public Response<User> findById(final Long id) {
        try {
            UserEntity userEntity = userElasticService.findById(id);
            if(userEntity == null){
                return new Response<>(ResponseStatus.NOT_FOUND);
            }
            return new Response<>(userMapper.mapToModel(userEntity));
        } catch (Exception e) {
            return new Response<>(ResponseStatus.NOT_FOUND);
        }
    }

    @Override
    public Response<User> add(final UserCmd userCmd) {
        userValidatorAdd.validate(userCmd);

        UserEntity userEntity = userMapper.mapToEntity(userCmd);
        User user = null;
        try {
            userElasticService.save(userEntity);
            return new Response<>(userMapper.mapToModel(userEntity));
        } catch (Exception e) {
            return new Response<>(ResponseStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public Response<User> edit(final Long userId, final UserCmd userCmd) {
        userValidatorEdit.validate(userId, userCmd);

        UserEntity userEntity = userElasticService.findById(userId);
        userMapper.updateEntityFromModel(userCmd, userEntity);
        User user = null;
        try {
            userElasticService.update(userEntity);
            user = userMapper.mapToModel(userEntity);
            return new Response<>(user);
        } catch (Exception e) {
            return new Response<>(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Response<?> delete(final Long userId) {
        userValidatorDelete.validate(userId);
        userElasticService.delete(userId);
        return new Response<>(ResponseStatus.OK, "User is deleted successfully!");
    }

}
