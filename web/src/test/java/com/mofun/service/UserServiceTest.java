package com.mofun.service;

import com.mofun.StartTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by runmain on 12/23/2016.
 */
public class UserServiceTest extends StartTests {
    @Autowired
    UserService userService;

    @Test
    public void deleteUserByIdTest() {
        try {
            userService.deleteUserById(3L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
