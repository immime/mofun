package com.mofun.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Properties;

/**
 * Created by runmain on 2017/1/22.
 */
@Configuration
@Order(-2)
public class AuthConfig {
    @Bean
    public Producer producer() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.image.width","100");
        properties.setProperty("kaptcha.image.height","34");
        properties.setProperty("kaptcha.textproducer.font.size","26");
        properties.setProperty("kaptcha.textproducer.char.length","4");
        properties.setProperty("kaptcha.textproducer.char.space","4");
        properties.setProperty("kaptcha.textproducer.font.color","black");
        properties.setProperty("kaptcha.border.color","LIGHT_GRAY");
        properties.setProperty("kaptcha.background.clear.from","WHITE");
//        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.DefaultNoise");
        properties.setProperty("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
        properties.setProperty("kaptcha.obscurificator.impl","com.google.code.kaptcha.impl.ShadowGimpy");
        properties.setProperty("kaptcha.textproducer.char.string","12A3BC5DEFZGY7XWVUT9SJRKQ0LPMN");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
