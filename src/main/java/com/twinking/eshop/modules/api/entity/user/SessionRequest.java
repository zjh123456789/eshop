package com.twinking.eshop.modules.api.entity.user;

/**
 * @Description:  获取session请求
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 14 12
 */
public class SessionRequest {

    /**
     * 微信生成请求码
     */
    private String code;
    /**
     * 微信昵称
     */
    private String nickName;
    /**
     * 性别（0 ：未知 1：男 2：女）
     */
    private String gender ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
