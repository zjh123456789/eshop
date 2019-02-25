package com.twinking.eshop.common.base.enums;

/**
 * @Description: 异常类型枚举
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 22 22
 */
public enum ExceptionEnum {

    SUCCESS(200, "处理完成"),
    ACCEPTED(202, "服务器已接受请求，但尚未处理"),
    NO_CONTENT(204, "服务器成功处理了请求，但没有返回任何内容"),
    ERRONEOUS_REQUEST(400, "参数为空或请求数据格式错误"),
    UNAUTHORIZED(401, "身份验证失败"),
    PROHIBIT(403, "服务器拒绝请求"),
    REQUEST_TIMEOUT(408, "服务器等候请求时发生超时"),
    INTERNAL_SERVER_ERROR(500, "服务器遇到错误，无法完成请求"),
    SERVICE_NOT_AVAILABLE(503, "服务器目前无法使用"),
    GATEWAY_TIMEOUT(504, "服务器作为网关或代理，但是没有及时从上游服务器收到请求"),
    HTTP_VERSION_IS_NOT_SUPPORTED(505, "服务器不支持请求中所用的 HTTP 协议版本");

    /**
     * 键
     */
    private int key;
    /**
     * 值
     */
    private String value;

    ExceptionEnum(int key, String value){
        this.key = key;
        this.value = value;
    }

    /**
     * 由键获得值
     * @param key 枚举键
     * @return
     */
    public static String getValueByKey(int key) {
        ExceptionEnum[] exceptionEnums = values();
        for (ExceptionEnum exceptionEnum : exceptionEnums) {
            if (exceptionEnum.getKey() == key) {
                return exceptionEnum.getValue();
            }
        }
        return "500";
    }


    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
