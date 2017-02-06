package com.mofun.controller;

import com.alibaba.fastjson.JSON;
import com.google.code.kaptcha.Constants;
import com.mofun.cons.Result;
import com.mofun.constant.UserConstant;
import com.mofun.domain.User;
import com.mofun.service.UserService;
import com.mofun.vo.index.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ResponseBody
    public Result reg(HttpServletRequest request, HttpServletResponse response, @RequestBody @Validated(UserVo.Reg.class) UserVo user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (!Objects.equals(user.getPassword(), user.getPwdConfirm())) {
            return Result.error("密码两次输入不一致");
        }
        if (!Objects.equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY), user.getImageCode().toUpperCase())) {
            return Result.error("验证码有误");
        }
        List<User> users = userService.findByEmail(user.getEmail());
        Assert.isTrue(users.size() == 0, "该邮箱已被注册,请核对");
        userService.register(user);
        return Result.ok(null);
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
    public Result login(@RequestBody @Validated(UserVo.Login.class) UserVo user, BindingResult bindingResult) throws Exception {
        log.info("user try login...{}", JSON.toJSONString(user));
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        List<User> users = userService.findByEmail(user.getEmail());
        if (users.isEmpty()) {
            log.info("can not find user by username:{}", user.getUserName());
            return Result.error("该邮箱尚未注册,请核对后再登录");
        }
        Assert.isTrue(users.size() == 1, "该邮箱已被多人注册无法登陆");
        User temp = users.get(0);
        users.clear();
        if (Objects.equals(UserConstant.USER_STATUS_REG, temp.getStatus())) {
            return Result.error("您还没有激活该账户，请登陆注册邮箱激活");
        }
        if (Objects.equals(UserConstant.USER_STATUS_CHANGING, temp.getStatus())) {
            return Result.error("密码已被修改,请登录邮箱激活");
        }
        if (Objects.equals(UserConstant.USER_STATUS_LOCK, temp.getStatus())) {
            return Result.error("该账户已被锁定，请发送邮件到：yuanmengliji@live.cn 申诉");
        }
        if (!Objects.equals(temp.getUserPwd(), user.getPassword())) {
            log.info("user can not match  with password{}", user);
            temp = null;
            return Result.error("不是有效的用户，请检查密码是否正确");
        }
        temp = null;
        log.info("login success：{}", user);
        return Result.ok(new ArrayList());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "htm/index/login";
    }
}
