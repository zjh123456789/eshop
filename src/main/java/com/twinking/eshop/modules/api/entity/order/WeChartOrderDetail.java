package com.twinking.eshop.modules.api.entity.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.order.enums.FinishStateEnum;
import com.twinking.eshop.modules.order.enums.TradeStateEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 订单详情
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 16 43
 */
public class WeChartOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private String orderId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 完成时间
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private Date finishDate;
    /**
     * 订单编号
     */
    private String orderNumber = "";
    /**
     * 交易状态
     */
    private String tradeState;
    /**
     * 订单状态
     */
    private String orderState;
    /**
     * 总价
     */
    private double totalPrice;
    /**
     * 总积分
     */
    private double totalIntegral;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 订单商品集合
     */
    private List<OrderDetailsGoods> goodsList = new ArrayList<>();

    /**
     * 构造函数
     * @param order 订单
     */
    public WeChartOrderDetail(Order order) {
        this.orderId = order.getId();
        this.createDate = order.getCreateDate();
        this.finishDate = order.getFinishDate();
        this.orderNumber = order.getOrderNumber();
        this.tradeState = TradeStateEnum.getValueByKey(order.getTradeState());
        this.orderState = FinishStateEnum.getValueByKey(order.getOrderState());
        this.totalPrice = order.getTotalPrice();
        this.totalIntegral = order.getTotalIntegral();
        this.address = order.getAddress();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(double totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetailsGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderDetailsGoods> goodsList) {
        this.goodsList = goodsList;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }
}
