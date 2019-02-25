package com.twinking.eshop.modules.api.entity.user;

import java.io.Serializable;

/**
 * @Description:  获取用户session类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 09 30
 */
public class SessionResult  implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识码
     */
    private String sn;

    public SessionResult (){}

    public SessionResult(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
