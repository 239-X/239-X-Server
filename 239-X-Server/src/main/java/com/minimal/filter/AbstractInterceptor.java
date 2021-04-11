package com.minimal.filter;

import com.minimal.common.api.dto.ResponseDto;
import com.minimal.common.sdk.enums.ResponseEnum;
import com.minimal.common.sdk.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author linzhiqiang
 * @date 2019/4/17
 */
public abstract class AbstractInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(AbstractInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ResponseEnum result;
        try {
            result = preFilter(request);
        } catch (Exception e) {
            logger.error("preHandle catch a exception:" + e.getMessage());
            result = ResponseEnum.FAIL;
        }
        if (ResponseEnum.SUCCESS.code.equals(result.code)) {
            return true;
        }
        handlerResponse(result, response);
        return false;
    }


    /**
     * 自定义pre处理
     *
     * @param request
     * @return
     */
    protected abstract ResponseEnum preFilter(HttpServletRequest request);

    /**
     * 错误处理事件
     *
     * @param result
     * @param response
     */
    private void handlerResponse(ResponseEnum result, HttpServletResponse response) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setCode(result.code);
        responseDto.setStatus(result.status);
        responseDto.setMessage(result.message);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            printWriter.write(JsonUtils.toJson(responseDto));
        } catch (Exception e) {
            logger.error("handlerResponse catch a exception:" + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
