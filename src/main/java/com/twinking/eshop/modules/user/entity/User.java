package com.twinking.eshop.modules.user.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.utils.excel.annotation.ExcelField;
import com.twinking.eshop.modules.sys.entity.Role;


/**
 * @Description:  用户类 包括后台管理员和客户端会员
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/15 09 39
 */
public class User extends DataEntity<User> {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 登录名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户标识
     */
    private String sn;
    /**
     * 性别
     */
    private String sex;
    /**
     * 电话
     */
    private String phone;
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 个性签名
     */
    private String sign;
    /**
     * 当前积分
     */
    private Double integral = 0.0;
    /**
     * 所属角色
     */
    private Role role;

    public User() {
        super();
    }

    public User(String id) {
        super(id);
    }

    public User(String id, String username) {
        super(id);
        this.username = username;
    }

    public User(Role role){
        super();
        this.role = role;
    }

    @ExcelField(title="昵称", align=2, sort=1)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @ExcelField(title="用户名", align=2, sort=2)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ExcelField(title="积分", align=2, sort=3)
    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    @ExcelField(title="性别", align=2, sort=4)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ExcelField(title="电话", align=2, sort=5)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ExcelField(title="邮箱", align=2, sort=6)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @ExcelField(title="个性签名", align=2, sort=7)
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
