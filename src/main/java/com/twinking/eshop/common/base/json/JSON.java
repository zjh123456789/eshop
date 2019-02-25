package com.twinking.eshop.common.base.json;

import com.twinking.eshop.common.base.enums.ExceptionEnum;

import java.io.Serializable;

/**
 * @Description:  返回JSON数据基类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 09 07
 */
public class JSON<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP响应码 默认响应状态码200
     */
    private int code;

    /**
     * 数据实体
     */
    private T data;

    /**
     * 请求标识
     */
    private String message;

    /**
     * 构造函数 无返回数据 无返回消息
     */
    public JSON() {
        this.code = ExceptionEnum.SUCCESS.getKey();
        this.message = ExceptionEnum.SUCCESS.getValue();
    }

    /**
     * 构造函数 无返回数据 无返回消息
     */
    public JSON(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getKey();
        this.message = exceptionEnum.getValue();
    }

    /**
     * 构造函数
     * @param data 返回数据
     * @param message 返回消息
     */
    public JSON(T data, String message) {
        this.data = data;
        this.message = message;
    }

    /**
     * 构造函数
     * @param data 返回数据
     * @param exceptionEnum 返回消息和状态码
     */
    public JSON(T data, ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getKey();
        this.data = data;
        this.message = exceptionEnum.getValue();
    }

    /**
     * 构造函数
     * @param data 返回数据
     */
    public JSON(T data) {
        this.data = data;
        this.code = ExceptionEnum.SUCCESS.getKey();
        this.message = ExceptionEnum.SUCCESS.getValue();
    }

    /**
     * 构造函数 无返回数据
     * @param message 返回消息
     */
    public JSON(String message) {
        this.message = message;
    }

    /**
     * 构造函数 无返回数据
     * @param code 响应状态码
     * @param message 返回消息
     */
    public JSON(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 完整构造函数
     * @param code
     * @param data 返回数据
     * @param message  返回消息
     */
    public JSON(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
