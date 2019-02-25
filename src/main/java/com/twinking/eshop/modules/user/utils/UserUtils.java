package com.twinking.eshop.modules.user.utils;

import com.twinking.eshop.common.config.Global;
import com.twinking.eshop.common.redis.JedisUtils;
import com.twinking.eshop.common.utils.SpringContextHolderUtils;
import com.twinking.eshop.modules.sys.dao.RoleDao;
import com.twinking.eshop.modules.sys.entity.Role;
import com.twinking.eshop.modules.user.dao.UserDao;
import com.twinking.eshop.modules.user.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Description:  用户工具类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/10 09 26
 */
public class UserUtils {

    private static UserDao userDao = SpringContextHolderUtils.getBean(UserDao.class);
    private static RoleDao roleDao = SpringContextHolderUtils.getBean(RoleDao.class);

    //会话失效时间
    public static final int SESSION_TIMEOUT = Global.getSessionTimeout();

    /**
     * 获取所有的角色
     * @return
     */
    public static List<Role> getRoleList() {
        List<Role> list = roleDao.findList(new Role());
        return list;
    }

    /**
     * 从数据库获取用户
     * @return
     */
    public static User get(String id) {
        return userDao.get(id);
    }

    /**
     * 获取当前用户
     * @return
     */
    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String id = request.getRequestedSessionId();
        return getUserCacheByKey(id);
    }

    /**
     * 将用户添加至Redis缓存
     * @param user
     */
    public static void addUserToCache(String key, User user){
        //添加至缓存
        JedisUtils.setObject(key, user, SESSION_TIMEOUT);
    }

    /**
     * 缓存是否存在key
     * @param key
     * @return
     */
    public static boolean cacheIsExitKey(String key){
        return JedisUtils.exists(key);
    }

    /**
     * 根据key获取用户
     */
    public static User getUserCacheByKey(String key) {
        if(cacheIsExitKey(key)){
            return (User) JedisUtils.getObject(key);
        }else
        {
            return null;
        }
    }

    /**
     * 根据id从缓存删除用户
     */
    public static void delUserCacheByKey(String key) {
        if(cacheIsExitKey(key)){
            JedisUtils.delObject(key);
        }
    }

    /**
     * 获取所有在线用户数
     */
    public static int getOnLineUserCount() {
        return JedisUtils.getResource().keys("*").size();
    }

    /**
     * 获取所有在线用户集合
     */
    public static List<User> getOnLineUserList() {
        List<User> list = new ArrayList<>();
        Set<String> set = JedisUtils.getResource().keys("*");
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            list.add((User) JedisUtils.getObject(it.next()));
        }
        return list;
    }

    /**
     * 根据用户名获取用户
     * @param name 用户名
     * @return
     */
    public static Object getByUserName(String name) {
        User u = new User();
        u.setUsername(name);
        List<User> list = userDao.findList(u);
        if(list.size()>0){
            return list.get(0);
        }else{
            return new User();
        }
    }
}
