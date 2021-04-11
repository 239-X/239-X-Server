package com.minimal.common.sdk.aop;

import com.alibaba.fastjson.JSON;
import com.minimal.common.sdk.annotation.RedisCacheResult;
import com.minimal.common.sdk.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static tk.mybatis.mapper.entity.EntityTable.DELIMITER;

/**
 * @description: redis 缓存切面
 * @Date : 2019/3/19 19:32
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Component
@Aspect
@Slf4j
public class RedisCacheResultAdvice {
    private Logger log = LoggerFactory.getLogger(RedisCacheResultAdvice.class);

    private static final String LOG_TITLE="【redis切面缓存】";

    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.minimal.common.sdk.annotation.RedisCacheResult)")
    private void annotationPoint() {
    }

    @Around("annotationPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceedResult = null;
        String prefix = null;
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        try {
            RedisCacheResult annotation = method.getAnnotation(RedisCacheResult.class);
            long expire = annotation.expire();
            String clazzName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            String key = getKey(clazzName, methodName, args);
            Object value = getCachedData(key);
            if (value == null) {
                synchronized (key) {
                    value = getCachedData(key);
                    if (value == null) {
                        proceedResult = joinPoint.proceed(args);
                    }
                }
                // 序列化查询结果
                final String jsonResult = serialize(proceedResult);
                final long keyExpire = getKeyExpire(joinPoint);
                if (keyExpire == 0) {
                    redisUtil.set(key, jsonResult);
                } else {
                    redisUtil.set(key, jsonResult, keyExpire);
                }
                log.info("{}获取缓存为空，直接进行查库",LOG_TITLE);
                return proceedResult;
            }
            proceedResult = JSON.parse((String) value);
            return proceedResult;
        } catch (Throwable throwable) {
            log.error("[CacheResultAdvice]:prefix->{}, method->{}, args->{}", prefix, method, JSON.toJSONString(joinPoint.getArgs()), throwable);
        }
        return proceedResult;
    }


    private long getKeyExpire(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Class clazz = targetMethod.getClass();
        RedisCacheResult cache = targetMethod.getAnnotation(RedisCacheResult.class);
        long expire = cache.expire();
        return expire;
    }

    private Object getCachedData(String key) {
        Object value = null;
        try {
            value = redisUtil.get(key);
        } catch (Exception e) {
            log.error("redis切面缓存从redis中获取数据异常。");
        }
        return value;
    }

    private String getKey(String clazzName, String methodName, Object[] args) {
        StringBuilder key = new StringBuilder("minimal:");
        key.append(clazzName);
        key.append(DELIMITER);
        key.append(methodName);
        key.append(DELIMITER);
        if (args.length > 0) {
            for (Object obj : args) {
                if (obj != null) {
                    key.append(obj.toString());
                    key.append(DELIMITER);
                }
            }
        }
        return key.toString();
    }

    protected String serialize(Object target) {
        if (target == null) {
            return "";
        }
        return JSON.toJSONString(target);
    }
}
