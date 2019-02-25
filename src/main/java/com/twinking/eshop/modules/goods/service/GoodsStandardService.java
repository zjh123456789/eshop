package com.twinking.eshop.modules.goods.service;

import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.modules.goods.dao.GoodsStandardDao;
import com.twinking.eshop.modules.goods.entity.GoodsStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:  商品规格业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsStandardService extends AbstractService<GoodsStandardDao,GoodsStandard> {

    @Autowired
    private GoodsStandardDao goodsStandardDao;

    /**
     * 新增或编辑保存商品规格
     * @param goodsStandard
     * @return
     */
    public String saveGoodsStandard(GoodsStandard goodsStandard) throws Exception{
        if(!goodsStandard.getIsNewRecord()){
            if(Constants.GOODS_UNDEFINED_GOODS_STANDARD_ID.equals(goodsStandard.getId())) {
                return "无法编辑未定义规格";
            }
            //从数据库取出记录的值
            GoodsStandard t = goodsStandardDao.get(goodsStandard.getId());
            //将编辑表单中的非NULL值覆盖数据库记录中的值
            MyBeanUtils.copyBeanNotNull2Bean(goodsStandard, t);
            //编辑表单保存
            this.save(t);
        }else{
            //新增表单保存
            this.save(goodsStandard);
        }
        return "保存成功";
    }

    /**
     * 批量删除商品规格
     * @param ids 删除数据的id
     * @return  成功条数
     */
    public int deleteAll(String ids){
        String idArray[] =ids.split(",");
        int effect = 0;
        for(String id : idArray){
            if(Constants.GOODS_UNDEFINED_GOODS_STANDARD_ID.equals(id)) {
                continue;
            }
            effect++;
            goodsStandardDao.delete(new GoodsStandard(id));
        }
        return effect;
    }
}
