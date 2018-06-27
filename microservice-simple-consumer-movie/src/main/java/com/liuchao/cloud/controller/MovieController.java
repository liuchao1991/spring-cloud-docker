package com.liuchao.cloud.controller;

import com.liuchao.cloud.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 电影控制器
 *
 * @author liuchao
 * @date 2018/6/27 22:11
 */
@RestController
@RequestMapping("/user")
@ConfigurationProperties(prefix = "user")
public class MovieController {

    // @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    public void setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return restTemplate.getForObject(userServiceUrl + id, User.class);
    }
}
