package com.example.topic.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 宋澳龙
 * @time 2019/12/11 12：00
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     * 配置了跨域。
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}