package com.twinking.eshop.modules.goods.entity;

import com.twinking.eshop.common.base.entity.DataEntity;

/**
 * @Description:  商品规格实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
public class GoodsStandard extends DataEntity<GoodsStandard> {

    /**
     * 商品规格名
     */
    private String name;

    public GoodsStandard() {
    }

    public GoodsStandard(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
