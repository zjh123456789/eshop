package com.twinking.eshop.modules.goods.service;

import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.common.utils.excel.ImportExcelUtils;
import com.twinking.eshop.modules.goods.dao.GoodsDao;
import com.twinking.eshop.modules.goods.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @Description:  商品业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 13
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodsService extends AbstractService<GoodsDao, Goods> {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 保存商品
     * @param goods 添加或编辑的商品信息
     * @return
     */
    public void saveGoods(Goods goods) throws Exception{
        if(!goods.getIsNewRecord()){
            //从数据库取出记录的值
            Goods t = goodsDao.get(goods.getId());
            //将编辑表单中的非NULL值覆盖数据库记录中的值
            MyBeanUtils.copyBeanNotNull2Bean(goods, t);
            //编辑表单保存
            this.save(t);
        }else{
            //新增表单保存
            this.save(goods);
        }
    }

    /**
     * 批量导入商品数据
     * @param file
     * @return
     */
    public String importGoods(MultipartFile file){
        try {
            int successNum = 0;
            int failureNum = 0;
            StringBuilder failureMsg = new StringBuilder();
            ImportExcelUtils ei = new ImportExcelUtils(file, 1, 0);
            List<Goods> list = ei.getDataList(Goods.class);
            for (Goods user : list){
                try{
                    this.save(user);
                    successNum++;
                }catch(ConstraintViolationException ex){
                    failureNum++;
                }catch (Exception ex) {
                    failureNum++;
                }
            }
            if (failureNum>0){
                failureMsg.insert(0, "，失败 "+failureNum+" 条商品记录。");
            }
            return "已成功导入 " + successNum + " 条商品记录" + failureMsg;
        } catch (Exception e) {
            e.printStackTrace();
            return "批量导入商品失败！失败信息："+e.getMessage();
        }
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
            effect++;
            goodsDao.delete(new Goods(id));
        }
        return effect;
    }

}
