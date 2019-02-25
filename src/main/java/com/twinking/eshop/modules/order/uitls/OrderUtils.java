package com.twinking.eshop.modules.order.uitls;

import com.twinking.eshop.common.constant.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description:  订单工具类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 12 32
 */
public class OrderUtils {

    /**
     * 生成订单号随机数类
     */
    private static Random createOrderRandom = new Random();

    /**
     * 生成订单号
     * @return
     */
    public static String createOrderNumber(){
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.ORDER_CREATE_NUMBER_DATE_FORMAT);
        // 获取5位随机数
        int rannum = (int) (createOrderRandom.nextDouble() * (99999 - 10000 + 1)) + 10000;
        String orderNumber = formatter.format(new Date()) + rannum;
        return orderNumber;
    }
}
