package com.mofun.config;

import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by runmain on 1/10/2017.
 */
@Configuration
@Order(-1)
public class ErrorPageConfig implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        if (status.is4xxClientError()) {
            return new ModelAndView("/htm/error/4xx");
        }
        return new ModelAndView("/htm/error/5xx");
    }
}
