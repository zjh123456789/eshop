package com.twinking.eshop.modules.goods.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.utils.excel.annotation.ExcelField;

/**
 * @Description:  商品类型实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
public class GoodsType extends DataEntity<GoodsType> {
    /**
     * 商品类型名
     */
    private String name;

    public GoodsType() {

    }

    public GoodsType(String id) {
        super(id);
    }

    @ExcelField(title="商品类型", align=2, sort=6)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
