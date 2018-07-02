package com.liuchao.cloud.controller;

import com.liuchao.cloud.entities.User;
import com.liuchao.cloud.feign.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 电影控制器
 *
 * @author liuchao
 * @date 2018/6/27 22:11
 */
@RestController
@Import(FeignClientsConfiguration.class)
public class MovieController {

    private UserFeignClient userUserFeignClient;
    private UserFeignClient adminUserFeignClient;


    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(UserFeignClient.class, "http://microservice-provider-user/");
        this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(UserFeignClient.class, "http://microservice-provider-user/");
    }

    @GetMapping("/user-user/{id}")
    public User findById(@PathVariable Long id) {
        return userUserFeignClient.findById(id);
    }

    @GetMapping("/admin-user/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return adminUserFeignClient.findById(id);
    }
}
