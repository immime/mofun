package com.mofun.service.impl;

import com.mofun.constant.SqlConstant;
import com.mofun.dao.UserMapper;
import com.mofun.domain.User;
import com.mofun.domain.UserExample;
import com.mofun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by runmain on 12/23/2016.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    @Transactional(value = SqlConstant.TRANSACTION_MANAGER_NAME, readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int deleteUserById(Long id) throws Exception {
        Assert.isTrue(id > 0, "request error ,user id must be great than zero");
        return userMapper.deleteByPrimaryKey(id);

    }

    @Override
    public User findByUserName(String username) throws Exception {
        Assert.notNull(username, "用户名不为空");
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return users.isEmpty() ? null : users.get(0);
    }
}
