package com.mofun.service.impl;

import com.mofun.constant.DigestUtils;
import com.mofun.constant.SqlConstant;
import com.mofun.constant.UserConstant;
import com.mofun.dao.UserMapper;
import com.mofun.domain.User;
import com.mofun.domain.UserExample;
import com.mofun.service.UserService;
import com.mofun.vo.index.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
    public int deleteUserById(Long id) {
        Assert.isTrue(id > 0, "request error ,user id must be great than zero");
        return userMapper.deleteByPrimaryKey(id);

    }

    @Override
    public User findByUserName(String username) {
        Assert.notNull(username, "request error,username must not be blank");
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> users = userMapper.selectByExample(example);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<User> findByEmail(String email) {
        Assert.notNull(email, "request error,email must not be blank");
        UserExample example = new UserExample();
        example.createCriteria().andEmailEqualTo(email);
        return userMapper.selectByExample(example);
    }

    @Override
    public boolean register(UserVo userVo) {
        User user = new User();
        user.setUserName(userVo.getUserName().trim());
        try {
            user.setUserPwd(DigestUtils.md5DigestAsHex(userVo.getPassword().trim().getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("密码不能包含非法字符");
        }
        user.setEmail(userVo.getEmail().trim());
        user.setMobilePhone(userVo.getMobilePhone());
        user.setCreateDate(new Date());
        user.setStatus(UserConstant.USER_STATUS_REG);
        return userMapper.insert(user) == 1;
    }
}
