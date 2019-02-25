package com.twinking.eshop.modules.api.service;

import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.modules.operate.dao.OperateDao;
import com.twinking.eshop.modules.operate.entity.Operate;
import com.twinking.eshop.modules.api.dao.ApiOperateDao;
import com.twinking.eshop.modules.api.entity.sellgoods.GoodsList;
import com.twinking.eshop.modules.api.entity.sellgoods.GoodsTypeList;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoods;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoodsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 15 09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiOperateService extends AbstractService<OperateDao, Operate> {

    @Autowired
    private ApiOperateDao apiOperateDao;

    /**
     * 根据商品id获取在售商品信息
     * @param id
     * @return
     */
    public SellGoodsDetail getSellGoodsDetail(String id){
        validationParameters(id);
        return apiOperateDao.getSellGoodsDetail(id);
    }

    /**
     * 最新上线商品 10个
     * @return
     */
    public GoodsList findNewGoodsList(){
        List<SellGoods> sellGoodsList = apiOperateDao.getNewGoodsList();
        GoodsList goodsList = new GoodsList();
        goodsList.setName("最近新品");
        goodsList.setPicture("#");
        goodsList.setList(sellGoodsList);
        return goodsList;
    }

    /**
     * 推荐商品
     * @return
     */
    public GoodsList findRecommendGoodsList(){
        List<SellGoods> sellGoodsList = apiOperateDao.getRecommendGoodsList();
        GoodsList goodsList = new GoodsList();
        goodsList.setName("推荐商品");
        goodsList.setPicture("#");
        goodsList.setList(sellGoodsList);
        return goodsList;
    }

    /**
     * 首发商品
     * @return
     */
    public GoodsList findFirstGoodsList(){
        List<SellGoods> sellGoodsList = apiOperateDao.getFirstGoodsList();
        GoodsList goodsList = new GoodsList();
        goodsList.setName("首发商品");
        goodsList.setPicture("#");
        goodsList.setList(sellGoodsList);
        return goodsList;
    }

    /**
     * 限时抢购商品
     * @return
     */
    public GoodsList findRushGoodsList(){
        List<SellGoods> sellGoodsList = apiOperateDao.getRushGoodsList();
        GoodsList goodsList = new GoodsList();
        goodsList.setName("限时抢购商品");
        goodsList.setPicture("#");
        goodsList.setList(sellGoodsList);
        return goodsList;
    }

    /**
     * 获取所有商品类别  不含未分类
     * @return
     */
    public GoodsTypeList findGoodsTypeList() {
        GoodsTypeList goodsTypeList = new GoodsTypeList();
        goodsTypeList.setTypeList(apiOperateDao.getGoodsTypeList());
        for (int i = 0; i < goodsTypeList.typeList.size(); i++) {
            //根据商品类型id把对应商品集合查出
            List<SellGoods> sellGoodsList = apiOperateDao.getGoodsListByType(goodsTypeList.typeList.get(i).getId());
            goodsTypeList.typeList.get(i).setGoodsList(sellGoodsList);
        }
        return goodsTypeList;
    }

    /**
     * 根据商品类型id获取所有商品
     * @return
     */
    public List<SellGoods> findGoodsListByType(String id){
        return apiOperateDao.getGoodsListByType(id);
    }
}
