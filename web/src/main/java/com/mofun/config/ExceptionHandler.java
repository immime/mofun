package com.mofun.config;

import com.mofun.cons.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by runmain on 2016/12/26.
 */
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handle(HttpServletRequest request, Exception e) {
        return Result.error(e.getMessage());
    }
}
