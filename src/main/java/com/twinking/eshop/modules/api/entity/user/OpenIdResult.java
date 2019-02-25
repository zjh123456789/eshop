package com.twinking.eshop.modules.api.entity.user;

/**
 * @Description: 请求openid 返回结果
 * @autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/30 13 33
 */
public class OpenIdResult {
    /**
     * 用户唯一标识
     */
    private String openid;
    /**
     * 回话秘钥
     */
    private String session_key;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }
}
