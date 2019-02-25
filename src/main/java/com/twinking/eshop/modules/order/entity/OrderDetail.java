package com.twinking.eshop.modules.order.entity;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.modules.integral.entity.Integral;

import java.util.List;

/**
 * @Description:  订单详情类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/21 13 23
 */
public class OrderDetail {

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单
     */
    private Order order;
    /**
     * 订单商品详情
     */
    private List<Integral> goodsList;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Integral> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Integral> goodsList) {
        this.goodsList = goodsList;
    }
}
