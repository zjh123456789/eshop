package com.twinking.eshop.modules.api.entity.sellgoods;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Description:  正在销售的商品详情
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 14 55
 */
public class SellGoodsDetail implements Serializable {

    public SellGoodsDetail(){}

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String id;
    /**
     * 名称
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String name;
    /**
     * 类型
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String type;
    /**
     * 规格
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String standard;
    /**
     * 售价
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private double sellPrice;
    /**
     * 原价
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private double originalPrice;
    /**
     * 库存
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private double inventory;
    /**
     * 积分
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private double integral;
    /**
     * 介绍
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String introduce;
    /**
     * 展示
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
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

    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
