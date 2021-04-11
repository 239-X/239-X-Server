package com.minimal.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import com.minimal.common.sdk.enums.ResponseEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author linzhiqiang
 * @date 2019/4/17
 */
@Component("rateLimitInterceptor")
public class RateLimitInterceptor extends AbstractInterceptor {
    private Logger logger = LoggerFactory.getLogger(RateLimitInterceptor.class);

    /**
     * 单机全局限流器(限制QPS为250)
     */
    private static final RateLimiter rateLimiter = RateLimiter.create(300);

    public static void setRate(double limiterQPS){
        rateLimiter.setRate(limiterQPS);
    }
    @Override
    protected ResponseEnum preFilter(HttpServletRequest request) {
        if (!rateLimiter.tryAcquire()) {
            logger.warn("限流中......");
            return ResponseEnum.RATE_LIMIT;
        }
        return ResponseEnum.SUCCESS;
    }
}
