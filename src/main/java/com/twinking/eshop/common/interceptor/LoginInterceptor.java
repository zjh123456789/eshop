package com.twinking.eshop.common.interceptor;

import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Description: 登陆拦截器 拦截未登录访问
 * @Autuor  朱景辉（1229585753@qq.com）
 * @Date 2018/9/26 09 39
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 该方法将在请求处理之前进行调用
     * */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String sessionID = request.getRequestedSessionId();
        String webName = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        if (StringUtils.isNotBlank(webName)) {
            basePath += "/" + webName;
        }
        if(StringUtils.isBlank(sessionID)){
            logger.debug("拒绝未授权访问。 url： {}" ,request.getRequestURI());
            response.sendRedirect(basePath + "/unauthorized");
            return false;
        }
        User user = UserUtils.getUserCacheByKey(sessionID);
        if (null == user) {
            response.sendRedirect(basePath + "/unauthorized");
            logger.debug("拒绝未授权访问。 url： {}" ,request.getRequestURI());
            return false;
        }
        return true;
    }

    /**
     * 该方法将在请求处理之后
     * @param request
     * @param response
     * @param obj
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object obj, ModelAndView modelAndView)
            throws Exception{
    }


    /**
     * 该方法将在整个请求结束之后，也就是在DispatcherServlet
     * 渲染了对应的视图之后执行。用于进行资源清理
     * @param request
     * @param response
     * @param obj
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object obj, Exception ex)
            throws Exception{
    }



}

