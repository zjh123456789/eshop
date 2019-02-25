package com.twinking.eshop.modules.api.entity.sellgoods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:  商品分类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/24 22 33
 */
public class GoodsTypeList implements Serializable {

    private static final long serialVersionUID = 1L;
     /**
     * 在售商品分类集合
     */
    public List<SellGoodsType> typeList = new ArrayList<>();

    public List<SellGoodsType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<SellGoodsType> typeList) {
        this.typeList = typeList;
    }
}
