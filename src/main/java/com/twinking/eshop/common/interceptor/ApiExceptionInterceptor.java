package com.twinking.eshop.common.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twinking.eshop.common.base.enums.ExceptionEnum;
import com.twinking.eshop.common.base.json.JSON;
import com.twinking.eshop.common.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description:  api调用的返回异常拦截器
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 22 09
 */
public class ApiExceptionInterceptor implements HandlerInterceptor {

    /**
     * response 返回头类型名
     */
    public static final String CONTENT_TYPE_NAME = "Content-type";
    /**
     * response 返回头类型值
     */
    public static final String CONTENT_TYPE_VALUE = "text/html;charset=UTF-8";
    /**
     * response 返回数据编码
     */
    public static final String CHARACTER_ENCODING = "UTF-8";

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 异常统一处理
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if(ex == null){
            return;
        }
        //发现异常才进行处理
        JSON<Object> objectJSON = null;
        if(ex instanceof NullPointerException){
            objectJSON = new JSON<>(ExceptionEnum.ERRONEOUS_REQUEST);
        }else if(ex instanceof ResourceAccessException){
            objectJSON = new JSON<>(ExceptionEnum.GATEWAY_TIMEOUT);
        }else if(ex instanceof UnauthorizedException){
            objectJSON = new JSON<>(ExceptionEnum.UNAUTHORIZED);
        }else if(ex instanceof IllegalArgumentException){
            objectJSON = new JSON<>(ExceptionEnum.ERRONEOUS_REQUEST);
        }else{
            objectJSON = new JSON<>(ExceptionEnum.INTERNAL_SERVER_ERROR);
        }
        logger.debug("异常统一处理。 url： {} 异常名 : {}" ,request.getRequestURI(), ex.getClass().getName());
        ex.printStackTrace();
        PrintWriter out = null;
        response.setHeader(CONTENT_TYPE_NAME, CONTENT_TYPE_VALUE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        try {
            out = response.getWriter();
            out.append(new ObjectMapper().writeValueAsString(objectJSON));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
