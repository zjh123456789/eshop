package com.twinking.eshop.modules.api.entity.sellgoods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 14 49
 */
public class GoodsList implements Serializable {

    public GoodsList(){}

    private static final long serialVersionUID = 1L;
    /**
     * 主题名
     */
    private String name;
    /**
     * 主题展示图片
     */
    private String picture;
    /**
     * 销售商品
     */
    public List<SellGoods> list = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SellGoods> getList() {
        return list;
    }

    public void setList(List<SellGoods> list) {
        this.list = list;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
