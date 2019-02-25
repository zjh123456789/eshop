package com.twinking.eshop.modules.goods.service;

import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.modules.goods.dao.GoodsTypeDao;
import com.twinking.eshop.modules.goods.entity.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:  商品类型业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsTypeService extends AbstractService<GoodsTypeDao, GoodsType> {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    /**
     * 新增或编辑保存商品类型
     * @param goodsType
     * @return
     */
    public String saveGoodsType(GoodsType goodsType) throws Exception{
        if(!goodsType.getIsNewRecord()){
            if(Constants.GOODS_UNSORET_GOODS_TYPE_ID.equals(goodsType.getId())) {
                return "不允许修改未分类类型";
            }
            //从数据库取出记录的值
            GoodsType t = goodsTypeDao.get(goodsType.getId());
            //将编辑表单中的非NULL值覆盖数据库记录中的值
            MyBeanUtils.copyBeanNotNull2Bean(goodsType, t);
            //编辑表单保存
            this.save(t);
        }else{
            //新增表单保存
            this.save(goodsType);
        }
        return "保存成功";
    }

    /**
     * 批量删除商品类型
     * @param ids 删除数据的id
     * @return  成功条数
     */
    public int deleteAll(String ids){
        String idArray[] =ids.split(",");
        int effect = 0;
        for(String id : idArray){
            if(Constants.GOODS_UNSORET_GOODS_TYPE_ID.equals(id)) {
                continue;
            }
            effect++;
            goodsTypeDao.delete(new GoodsType(id));
        }
        return effect;
    }
}
