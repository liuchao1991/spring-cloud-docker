package com.liuchao.cloud.feign;

import com.liuchao.cloud.config.FeignConfiguration;
import com.liuchao.cloud.entities.User;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 使用@FeignClient的configuration属性, 指定feign的配置类
 *
 * @author liuchao
 * @date 2018/6/30 21:09
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
    /**
     * 使用feign自带的注解@RequestLine
     * @see https://github.com/OpenFeign/feign
     * @param id 用户ID
     * @return 用户信息
     */
    @RequestLine("GET /user/{id}")
    public User findById(@Param("id") Long id);

}
