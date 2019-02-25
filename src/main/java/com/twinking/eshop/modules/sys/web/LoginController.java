package com.twinking.eshop.modules.sys.web;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.base.web.BaseController;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.service.UserService;
import com.twinking.eshop.modules.user.utils.UserUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Description:   登录控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/11 09 26
 */
@Controller
@RequestMapping("")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = {"login", ""})
    public ModelAndView login(String username, String password){
        if(username == null || password == null){
            return new ModelAndView("modules/sys/login");
        }
        User user = userService.findUserByUsername(new User(null, username));
        //手机端用户和密码错误不允许登陆
        if(user == null || Constants.ROLE_CUSTOMER_ROLE_ID.equals(user.getRole().getId()) ||
                !user.getPassword().equals(password)){
            return new ModelAndView("modules/sys/login","message","用户名不存在或密码错误！");
        }
        if(User.STATE_FLAG_DELETE.equals(user.getStateFlag().trim())){
            return new ModelAndView("modules/sys/login","message","该用户已被禁用！");
        }

        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        UserUtils.addUserToCache(req.getRequestedSessionId(), user);
        return new ModelAndView("/index","user", user);
    }

    /**
     * 未授权拦截
     * @return
     */
    @RequestMapping("unauthorized")
    public ModelAndView unauthorized(){
        return new ModelAndView("/error/unauthorized");
    }

    /**
     * TODO 获取菜单 暂不做任何处理
     * @return
     */
    @RequestMapping("getMenus")
    @ResponseBody
    public String getMenus()throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String basePath = request.getSession().getServletContext().getRealPath("/").replace('\\', '/');
        String fileName = basePath + "static/main/json/navs.json";
        String res = FileUtils.readFileToString(new File(fileName), "UTF-8");
        return res;
    }


}
