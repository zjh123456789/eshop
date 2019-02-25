package com.twinking.eshop.modules.api.entity.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.twinking.eshop.modules.order.enums.FinishStateEnum;
import com.twinking.eshop.modules.order.enums.TradeStateEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 订单列表 只显示第一件商品
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 16 43
 */
public class WeChartOrderList implements Serializable {

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
     * 订单编号
     */
    private String orderNumber;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 商品展示
     */
    private String goodsPicture;
    /**
     * 商品规格
     */
    private String goodsStandard;
    /**
     * 购买数量
     */
    private double number;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = TradeStateEnum.getValueByKey(tradeState);
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = FinishStateEnum.getValueByKey(orderState);
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

    public String getGoodsStandard() {
        return goodsStandard;
    }

    public void setGoodsStandard(String goodsStandard) {
        this.goodsStandard = goodsStandard;
    }
}
