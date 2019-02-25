package com.twinking.eshop.modules.goods.dao;

import com.twinking.eshop.common.base.dao.AbstractDao;
import com.twinking.eshop.common.base.dao.MyBatisDao;
import com.twinking.eshop.modules.goods.entity.Goods;

/**
 * @Description:  商品持久层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@MyBatisDao
public interface GoodsDao extends AbstractDao<Goods> {

    /**
     * 更新库存
     * @param goods
     * @return
     */
    public int updateInventory(Goods goods);

}
