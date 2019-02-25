package com.twinking.eshop.modules.operate.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.modules.goods.entity.Goods;

import java.util.Date;

/**
 * @Description:  运营商品实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 13 29
 */
public class Operate extends DataEntity<Operate> {
    /**
     * 商品
     */
    private Goods goods;
    /**
     * 销售价格
     */
    private double sellPrice;
    /**
     * 原价
     */
    private double originalPrice;
    /**
     * 起始日期
     */
    private Date startDate;
    /**
     * 截止日期
     */
    private Date endDate;
    /**
     * 积分
     */
    private double integral;
    /**
     * 过期状态
     */
    private int sellState;

    public Operate() {
    }

    public Operate(String id) {
        super(id);
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public int getSellState() {
        return sellState;
    }

    public void setSellState(int sellState) {
        this.sellState = sellState;
    }
}
