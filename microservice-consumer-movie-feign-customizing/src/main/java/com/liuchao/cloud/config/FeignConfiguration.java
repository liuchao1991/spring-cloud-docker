package com.liuchao.cloud.config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 该类为Feign的配置类
 * 注意: 该类不应该在主应用程序上下文的@Component中
 *
 * @author liuchao
 * @date 2018/6/30 21:26
 */
@Configuration
public class FeignConfiguration {
    /**
     * 将契约改为feign原生的默认契约, 这样就可以使用feign自带的注解了
     * @return 默认的feign契约
     */
    @Bean
    public Contract feginContract() {
        return new feign.Contract.Default();
    }
}
