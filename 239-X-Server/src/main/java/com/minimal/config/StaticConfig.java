package com.minimal.config;

/**
 * Created by administrator on 2019/4/18.
 */

import com.minimal.filter.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linzhiqiang
 * @date 2018-07-31
 * @deprecated 设置静态参数初始化
 */
@Configuration
public class StaticConfig {
    @Value("${limiterQPS}")
    private double limiterQPS;

    @Bean
    public int initStatic() {
        RateLimitInterceptor.setRate(limiterQPS);
        return 0;
    }
}