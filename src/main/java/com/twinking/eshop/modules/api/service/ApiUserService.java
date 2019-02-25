package com.twinking.eshop.modules.api.service;

import com.alibaba.fastjson.JSONObject;
import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.mapper.JsonMapper;
import com.twinking.eshop.common.redis.JedisUtils;
import com.twinking.eshop.common.utils.MD5Utils;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.modules.api.entity.user.OpenIdResult;
import com.twinking.eshop.modules.sys.entity.Role;
import com.twinking.eshop.modules.user.dao.UserDao;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;
import com.twinking.eshop.modules.api.entity.user.SessionRequest;
import com.twinking.eshop.modules.api.entity.user.SessionResult;
import com.twinking.eshop.modules.api.entity.user.UserInfo;
import com.twinking.eshop.modules.api.utils.WeChartUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

/**
 * @Description:  微信用户业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 21 31
 */
@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class ApiUserService extends AbstractService<UserDao, User> {

    @Autowired
    private UserDao userDao;

    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(ApiUserService.class);

    /**
     * 默认用户名
     */
    private static final String DEFAULT_USERNAME = "会员";
    /**
     * openid名
     */
    private static final String OPENID_NAME = "openid";

    /**
     * 根据用户传进来的code去微信服务器获取用户唯一标示  如未注册自动注册
     * @param sessionRequest 请求数据 code 昵称 性别
     * @return 用户请求标识sn
     */
    public SessionResult getSession(SessionRequest sessionRequest){
        //数据非空检查
        validationParameters(sessionRequest.getCode(), sessionRequest.getGender(), sessionRequest.getNickName());
        ResponseEntity<String> responseEntity = WeChartUtils.getOpenId(sessionRequest.getCode());
        if(!responseEntity.getBody().contains(OPENID_NAME)){
            throw new ResourceAccessException("获取openid失败");
        }
        OpenIdResult openIdResult = (OpenIdResult) JsonMapper.fromJsonString(responseEntity.getBody(), OpenIdResult.class);
        String sn = MD5Utils.getMD5(openIdResult.getOpenid());
        SessionResult sessionResult = new SessionResult(sn);
        //添加会员到数据库
        autoRegisteUser(sessionRequest, sn, openIdResult);
        return sessionResult;
    }

    /**
     * 自动注册会员 若已注册 则不进行任何操作
     * @param sn 唯一标识码
     */
    private void autoRegisteUser(SessionRequest sessionRequest, String sn, OpenIdResult openIdResult) {
        User user = new User();
        user.setSn(sn);
        User findUer = findUserBySN(user);
        if(null != findUer){
            JedisUtils.setObject(sn, findUer, UserUtils.SESSION_TIMEOUT);
            return;
        }
        //角色设为会员
        user.setRole(new Role(Constants.ROLE_CUSTOMER_ROLE_ID));
        user.setUsername(openIdResult.getOpenid());
        user.setNickname(sessionRequest.getNickName());
        //微信接口中的性别 0：未知 1：男 2：女
        user.setSex("2".equals(sessionRequest.getGender()) ? "0" : sessionRequest.getGender());
        user.setPassword(openIdResult.getSession_key());
        user.setSn(sn);
        try{
            save(user);
        }catch (UncategorizedSQLException ex){
            //微信名中出现不合法符号导致异常自动处理
            user.setNickname(DEFAULT_USERNAME);
            userDao.insert(user);
        }
    }

    /**
     * 获取用户积分
     * @param sn 用户标识sn
     * @return 积分
     */
    public Double findIntegralBySN(String sn){
        validationParameters(sn);
        User user = getUserBySN(sn, userDao);
        return user.getIntegral();
    }

    /**
     * 根据sn查询用户
     * @param user 用户标识sn
     * @return 用户
     */
    public User findUserBySN(User user){
        return userDao.getUserBySN(user);
    }

    /**
     * 根据sn查询用户
     * @param sn 用户标识sn
     * @return 用户基本信息
     */
    public UserInfo findUserInfoBySN(String sn){
        validationParameters(sn);
        getUserBySN(sn, userDao);
        return userDao.getUserInfoBySN(sn);
    }

    /**
     * 更新用户信息
     * @param userInfo 用户基本信息
     */
    public void updateUserInfoBySN(UserInfo userInfo) throws Exception{
        validationParameters(userInfo.getSn());
        getUserBySN(userInfo.getSn(), userDao);
        //从数据库取出记录的值
        UserInfo uf = userDao.getUserInfoBySN(userInfo.getSn());
        //将非NULL值覆盖数据库记录中的值
        MyBeanUtils.copyBeanNotNull2Bean(userInfo, uf);
        userDao.updateUserInfoBySN(userInfo);
    }


}
