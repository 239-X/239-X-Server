package com.minimal.common.sdk.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * 防重拦截器
 * 
 * <p>当开启严格校验模式，要求所有请求必须附带请求流水，拒绝没有流水号的请求；
 * <br>反之，使用宽松校验模式，放行没有附带流水的请求
 * 
 * <p>允许重复提交的间隔时间，即间隔时间外的同一请求流水，不做防重校验
 * 
 * <p>请求流水通过请求头{@value #SERIAL_REQUEST_HEADER}附带
 * 
 * @author wubin
 *
 */
public class AntiReplayInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AntiReplayInterceptor.class);
	
	private static final String SERIAL_REQUEST_HEADER = "Infun-Request-Serial";
	
	private boolean strict = false;
	private long interval = 5L;
	
	@Autowired
	private StringRedisTemplate template;
	
	/**
	 * 构造方法，默认使用宽松校验模式, 5秒间隔
	 */
	public AntiReplayInterceptor() {}
	
	/**
	 * 构造方法
	 * @param strict 是否开启严格模式
	 * @param interval 允许重复提交的间隔时间
	 */
	public AntiReplayInterceptor(boolean strict, long interval) {
		this.strict = strict;
		this.interval = interval;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String requestSerial = request.getHeader(SERIAL_REQUEST_HEADER);
		String requestURI = request.getRequestURI();
		
		if (!StringUtils.hasText(requestSerial)) {
			// 缺少请求流水
			if (strict) {
				logger.warn("当前请求头没有附带请求流水号，拒绝请求 [requestURI={}]", requestURI);
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "缺少请求流水号");
				return false;
			} else {
				logger.debug("当前请求头没有附带请求流水号，放行请求 [requestURI={}]", requestURI);
				return true;
			}
		}
		
		String key = requestURI + ":" + requestSerial;
		if (isReplay(key)) {
			// 重复请求
			logger.warn("存在重复请求 [requestURI={}, serial={}]", request.getRequestURI(), requestSerial);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "重复提交");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

	private boolean isReplay(String key) {
		BoundValueOperations<String, String> boundValueOps = template.boundValueOps(key);
		String value = boundValueOps.getAndSet(key);
		boundValueOps.expire(interval, TimeUnit.SECONDS);
		return !StringUtils.isEmpty(value);
	}
}
