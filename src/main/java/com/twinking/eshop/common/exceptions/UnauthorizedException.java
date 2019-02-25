package com.twinking.eshop.common.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 未授权异常
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 11 37
 */
public class UnauthorizedException extends RuntimeException{

    /**
     * 日志对象
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 异常信息
     */
    private String info;

    public UnauthorizedException(String info) {
        super();
        this.info = info;
        logger.error(info);
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
