package com.mofun.service;

import com.mofun.domain.User;
import com.mofun.vo.index.UserVo;

import java.util.List;

/**
 * Created by runmain on 12/23/2016.
 */
public interface UserService {

    int deleteUserById(Long id);
    User findByUserName(String username);
    List<User> findByEmail(String email) ;

    boolean register(UserVo user);
}
