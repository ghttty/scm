package com.fantaike.scm.service.impl;

import com.fantaike.scm.config.datasource.Dynamic;
import com.fantaike.scm.constants.DataSourceType;
import com.fantaike.scm.dao.UserMapper;
import com.fantaike.scm.model.User;
import com.fantaike.scm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Dynamic(DataSourceType.Slave)
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
