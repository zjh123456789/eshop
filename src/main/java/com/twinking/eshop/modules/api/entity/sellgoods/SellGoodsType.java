package com.twinking.eshop.modules.api.entity.sellgoods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:  商品类别
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/24 22 56
 */
public class SellGoodsType implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private String id;
    /**
     * 商品类型名称
     */
    private String name;
    /**
     * 销售商品
     */
    public List<SellGoods> goodsList = new ArrayList<>();

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

    public List<SellGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<SellGoods> goodsList) {
        this.goodsList = goodsList;
    }
}
