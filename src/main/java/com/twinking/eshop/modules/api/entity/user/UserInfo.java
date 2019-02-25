package com.twinking.eshop.modules.api.entity.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Description:  用户信息
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 22 25
 */
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户表示
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String sn;
    /**
     * 电话
     */
    private String phone = "";
    /**
     * 邮箱
     */
    private String mail = "";
    /**
     * 个性签名
     */
    private String sign = "";
    /**
     * 当前积分
     */
    private Double integral = 0.0;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
