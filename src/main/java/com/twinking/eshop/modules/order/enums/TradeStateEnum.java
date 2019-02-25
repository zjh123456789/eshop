package com.twinking.eshop.modules.order.enums;

import com.twinking.eshop.common.constant.Constants;

/**
 * @Description:  交易状态枚举
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 14 10
 */
public enum TradeStateEnum {

    NotPay(Constants.ORDER_UN_PAY_STATE, "未支付"),
    AlreadyPay(Constants.ORDER_ALREADY_PAY_STATE, "已支付"),
    RefundIng(Constants.ORDER_REFUNING_STATE, "退款中"),
    AlreadyRefund(Constants.ORDER_ALREADY_REFUND_STATE, "已退款"),
    UnableRefund(Constants.ORDER_UNABLE_REFUND_STATE, "无法退款");

    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;

    TradeStateEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    /**
     * 由键获得值
     * @param key 枚举键
     * @return
     */
    public static String getValueByKey(String key) {
        TradeStateEnum[] payStateEnums = values();
        for (TradeStateEnum payStateEnum : payStateEnums) {
            if (payStateEnum.getKey().equals(key)) {
                return payStateEnum.getValue();
            }
        }
        return Constants.ORDER_UN_PAY_STATE;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
