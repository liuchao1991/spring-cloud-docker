package com.liuchao.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignLogConfiguration
 *
 * @author liuchao
 * @date 2018/7/1 16:39
 */
@Configuration
public class FeignLogConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        // return Logger.Level.FULL;
        return Logger.Level.BASIC;
    }
}
