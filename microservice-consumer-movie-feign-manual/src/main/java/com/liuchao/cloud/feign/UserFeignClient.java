package com.liuchao.cloud.feign;

import com.liuchao.cloud.entities.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UserFeignClient
 *
 * @author liuchao
 * @date 2018/6/30 21:09
 */
// @FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}
