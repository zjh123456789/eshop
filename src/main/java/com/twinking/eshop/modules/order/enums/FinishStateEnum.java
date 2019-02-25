package com.twinking.eshop.modules.order.enums;

import com.twinking.eshop.common.constant.Constants;

/**
 * @Description:  订单完成状态枚举
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 14 10
 */
public enum FinishStateEnum {

    UnFinish(Constants.ORDER_UN_FINISH_STATE, "未完成"),
    AlreadyFinish(Constants.ORDER_ALREADY_FINISH_STATE, "已完成"),
    Canceling(Constants.ORDER_CANCELING_STATE, "取消中"),
    Cancel(Constants.ORDER_CANCEL_STATE, "已取消"),
    UnableCancel(Constants.ORDER_UNABLE_CANCEL_STATE, "无法取消");

    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;

    FinishStateEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    /**
     * 由键获得值
     * @param key 枚举键
     * @return
     */
    public static String getValueByKey(String key) {
        FinishStateEnum[] finishStateEnums = values();
        for (FinishStateEnum finishStateEnum : finishStateEnums) {
            if (finishStateEnum.getKey().equals(key)) {
                return finishStateEnum.getValue();
            }
        }
        return Constants.ORDER_UN_FINISH_STATE;
    }


    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

}
