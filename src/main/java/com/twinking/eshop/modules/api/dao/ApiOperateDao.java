package com.twinking.eshop.modules.api.dao;

import com.twinking.eshop.common.base.dao.MyBatisDao;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoods;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoodsDetail;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoodsType;

import java.util.List;

/**
 * @Description:
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 14 54
 */
@MyBatisDao
public interface ApiOperateDao {

    /**
     * api模块 根据商品id获取在售商品信息
     * @param id
     * @return
     */
    public SellGoodsDetail getSellGoodsDetail(String id);

    /**
     * api模块 最新上线商品 10个
     * @return
     */
    public List<SellGoods> getNewGoodsList();

    /**
     * api模块 推荐商品
     * @return
     */
    public List<SellGoods> getRecommendGoodsList();

    /**
     * api模块 首发商品
     * @return
     */
    public List<SellGoods> getFirstGoodsList();

    /**
     * api模块 限时抢购商品
     * @return
     */
    public List<SellGoods> getRushGoodsList();

    /**
     * api模块 获得所有在售商品
     * @return
     */
    public List<SellGoods> findAllSellGoodsList();

    /**
     * api模块 获取所有商品类别 不含未分类
     * @return
     */
    public List<SellGoodsType> getGoodsTypeList();

    /**
     * api模块 根据商品类型id获取所有商品
     * @return
     */
    public List<SellGoods> getGoodsListByType(String id);
}
