package com.twinking.eshop.modules.api.entity.order;

import java.io.Serializable;

/**
 * @Description:  下单参数
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/25 16 48
 */
public class OrderRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String goodId;

    private int number;

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
