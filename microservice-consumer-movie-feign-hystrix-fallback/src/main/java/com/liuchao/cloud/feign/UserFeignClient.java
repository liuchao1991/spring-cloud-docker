package com.liuchao.cloud.feign;

import com.liuchao.cloud.entities.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign的fallback测试
 * 使用@FeignFallback属性指定回退类
 * @author liuchao
 * @date 2018/6/30 21:09
 */
@FeignClient(name = "microservice-provider-user", fallback = FeignClientFallback.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}

/**
 * 回退FeignClientFallback需实现Feign Client接口
 * FeignClientFallback也可以是public class,没有区别
 */
@Component
class FeignClientFallback implements UserFeignClient {

    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setUsername("默认用户");
        return user;
    }
}
