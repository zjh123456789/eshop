package com.twinking.eshop.modules.goods.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.utils.excel.annotation.ExcelField;

/**
 * @Description:  商品实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
public class Goods extends DataEntity<Goods> {
    /**
     * 商品类型
     */
    private GoodsType type;
    /**
     * 商品规格
     */
    private GoodsStandard goodsStandard;
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品介绍
     */
    private String introduce;
    /**
     * 展示链接
     */
    private String picture;
    /**
     * 库存
     */
    private double inventory;

    public Goods() {
    }

    public Goods(String id) {
        super(id);
    }

    @ExcelField(title="名称", align=2, sort=1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title="商品类型", align=2, sort=2,value = "getType.name")
    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    @ExcelField(title="描述", align=2, sort=3)
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    @ExcelField(title="图片地址", align=2, sort=4)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @ExcelField(title="库存量", align=2, sort=5)
    public double getInventory() {
        return inventory;
    }

    public void setInventory(double inventory) {
        this.inventory = inventory;
    }

    @ExcelField(title="商品规格", align=2, sort=6,value = "goodsStandard.name")
    public GoodsStandard getGoodsStandard() {
        return goodsStandard;
    }

    public void setGoodsStandard(GoodsStandard goodsStandard) {
        this.goodsStandard = goodsStandard;
    }
}
