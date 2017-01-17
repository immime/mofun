package com.mofun.service;

import com.mofun.domain.User;

/**
 * Created by runmain on 12/23/2016.
 */
public interface UserService {

    int deleteUserById(Long id) throws Exception;
    User findByUserName(String username)throws  Exception;
}
