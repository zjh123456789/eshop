package com.twinking.eshop.modules.api.entity.order;

import com.twinking.eshop.modules.integral.entity.Integral;

import java.io.Serializable;

/**
 * @Description: 订单详情商品
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/27 15 27
 */
public class OrderDetailsGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String title;
    /**
     * 商品展示
     */
    private String image;
    /**
     * 商品规格
     */
    private String standard;
    /**
     * 商品原价
     */
    private Double originalPrice;
    /**
     * 商品售价
     */
    private Double price;
    /**
     * 商品积分
     */
    private Double integral;
    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 构造函数
     * @param integral 积分详情
     */
    public OrderDetailsGoods(Integral integral) {
        this.title = integral.getGoodsName();
        this.image = integral.getGoodsPicture();
        this.standard = integral.getGoodsStandardName();
        this.originalPrice = integral.getGoodsOriginalPrice();
        this.price = integral.getGoodsSellPrice();
        this.integral = integral.getChangeIntegral();
        this.num = integral.getGoodsByNumber();
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
