package com.twinking.eshop.common.constant;

import com.twinking.eshop.common.config.Global;

/**
 * @Description:  常量池类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 22 31
 */
public class Constants {

    /**
     *  删除标记（0：正常；1：禁用 ；）
     */
    public static final String STATE_FLAG_NORMAL = "0";
    public static final String STATE_FLAG_DELETE = "1";

    /**
     *   获取微信openidUrl
     */
    public static final String URL_GET_WX_OPENID =
            "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";

    /**
     *   微信appid
     */
    public static final String WEIXIN_APPID_OPENID = Global.getConfig("weixin.appid");

    /**
     *  微信secret
     */
    public static final String WEIXIN_SECRET_OPENID = Global.getConfig("weixin.secret");

    /**
     *  微信grant_type
     */
    public static final String WEIXIN_GRANT_TYPE_OPENID = "authorization_code";

    /**
     *   性别男id
     */
    public static final String USER_MAN_VALUE = "1";
    /**
     *  超级管理员id
     */
    public static final String USER_SUPER_ADMIN_ID = "0";


    /**
     *  未定义商品规格id
     */
    public static final String GOODS_UNDEFINED_GOODS_STANDARD_ID = "0";

    /**
     *  未分类商品类型id
     */
    public static final String GOODS_UNSORET_GOODS_TYPE_ID = "0";

    /**
     *  超级管理员角色id
     */
    public static final String ROLE_SUPER_ADMIN_ROLE_ID = "0";

    /**
     *  会员角色id
     */
    public static final String ROLE_CUSTOMER_ROLE_ID = "1";

    /**
     *  正在销售中商品正常状态
     */
    public static final int OPERATE_GOODS_SELL_STATE_NORMAL = 0;

    /**
     *  正在销售中商品禁用状态
     */
    public static final int OPERATE_GOODS_SELL_STATE_BAND = 1;

    /**
     *  正在销售中商品过期状态
     */
    public static final int OPERATE_GOODS_SELL_STATE_BE_OVERDUE = 2;

    /**
     *  生成订单号时间戳格式
     */
    public static final String ORDER_CREATE_NUMBER_DATE_FORMAT = "yyyyMMddhhmmssSSS";

    /**
     *  订单未支付
     */
    public static final String ORDER_UN_PAY_STATE = "0";

    /**
     *  订单已支付
     */
    public static final String ORDER_ALREADY_PAY_STATE = "1";

    /**
     *  订单退款中
     */
    public static final String ORDER_REFUNING_STATE = "2";

    /**
     *  订单已退款
     */
    public static final String ORDER_ALREADY_REFUND_STATE = "3";

    /**
     *  订单无法退款
     */
    public static final String ORDER_UNABLE_REFUND_STATE = "4";

    /**
     *  订单未完成
     */
    public static final String ORDER_UN_FINISH_STATE = "0";

    /**
     *  订单已完成
     */
    public static final String ORDER_ALREADY_FINISH_STATE = "1";

    /**
     *  订单取消中
     */
    public static final String ORDER_CANCELING_STATE = "2";

    /**
     *  订单已取消
     */
    public static final String ORDER_CANCEL_STATE = "3";

    /**
     *  订单无法取消
     */
    public static final String ORDER_UNABLE_CANCEL_STATE = "4";

}
