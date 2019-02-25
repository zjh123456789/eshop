package com.twinking.eshop.modules.user.dao;

import com.twinking.eshop.common.base.dao.AbstractDao;
import com.twinking.eshop.common.base.dao.MyBatisDao;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.api.entity.user.UserInfo;

import java.util.List;

/**
 * @Description:  用户管理持久层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/10 09 26
 */
@MyBatisDao
public interface UserDao extends AbstractDao<User> {

    /**
     * 根据登录名称查询用户
     * @param user
     * @return
     */
    public User getByUserName(User user);

    /**
     * 根据sn查询用户
     * @param user
     * @return
     */
    public User getUserBySN(User user);

    /**
     * 查询全部用户数目
     * @return
     */
    public long findAllCount(User user);

    /**
     * 更新用户密码
     * @param user
     * @return
     */
    public int updatePasswordById(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateUserInfo(User user);

    /**
     * 获取会员集合
     * @param user
     * @return
     */
    public List<User> findAllCustomer(User user);

    /**
     * 接口部分
     */

    /**
     * 根据ID获取用户
     * @param user
     * @return
     */
    public User getUserById(User user);

    /**
     * 根据sn更新积分
     * @param user
     * @return
     */
    public int updateIntegralBySN(User user);

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    public int updateUserInfoBySN(UserInfo userInfo);

    /**
     * 获取用户信息
     * @param sn
     * @return
     */
    public UserInfo getUserInfoBySN(String sn);


}
