package com.liuchao.cloud.controller;

import com.liuchao.cloud.entities.User;
import com.liuchao.cloud.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 电影控制器
 *
 * @author liuchao
 * @date 2018/6/27 22:11
 */
@RestController
@RequestMapping("/user")
public class MovieController {

    @Autowired
    private UserFeignClient userFeignClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }
}
