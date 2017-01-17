package com.mofun.controller;

import com.mofun.cons.Result;
import com.mofun.domain.User;
import com.mofun.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by runmain on 12/22/2016.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    RedisTemplate redisTemplate;
    private Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
    public String register() {
        return "htm/index/register";
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String change() {
        return "htm/index/change";
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody(required = false) User user) throws Exception {
        Assert.notNull(user, "信息不能为空");
        log.info("user try login...{}", user);
        User user1 = userService.findByUserName(user.getUserName());
        if (user1 == null) {
            log.info("can not find user by username:{}", user.getUserName());
            return Result.error("不是有效的用户，请确认用户是否正确");
        }
        if (!Objects.equals(user1.getUserPwd(), user.getUserPwd())) {
            log.info("user can not match  with password{}", user);
            return Result.error("不是有效的用户，请检查用户和密码是否匹配");
        }
        log.info("login success：{}", user);
        return Result.ok(new ArrayList());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "htm/index/login";
    }
}
