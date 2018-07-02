package com.liuchao.cloud.feign;

import com.liuchao.cloud.entities.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UserFeignClient
 *
 * @author liuchao
 * @date 2018/6/30 21:09
 */
@FeignClient(name = "microservice-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);
}

@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findById(Long id) {
                // 日志最好放在各个fallback方法中, 而不要直接放在create方法中
                // 否则在引用启动时, 就会打印该日志
                // 详见https://github.com/spring-cloud/spring-cloud-netflix/issues/1471
                LOGGER.info("fallback; reason was:", throwable);
                User user = new User();
                user.setId(1L);
                user.setName("默认用户");
                return user;
            }
        };
    }
}
