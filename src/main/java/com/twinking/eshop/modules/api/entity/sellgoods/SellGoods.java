package com.twinking.eshop.modules.api.entity.sellgoods;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @Description:  正在销售的商品概要
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 14 55
 */
public class SellGoods implements Serializable {

    public SellGoods(){}

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 规格
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private String standard;
    /**
     * 售价
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private Double sellPrice;
    /**
     * 原价
     */
    @JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
    private Double originalPrice;
    /**
     * 展示
     */
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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
