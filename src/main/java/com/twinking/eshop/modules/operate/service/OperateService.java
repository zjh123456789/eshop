package com.twinking.eshop.modules.operate.service;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.modules.operate.dao.OperateDao;
import com.twinking.eshop.modules.operate.entity.Operate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Description:  运营业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 13 29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperateService extends AbstractService<OperateDao, Operate> {

    @Autowired
    private OperateDao operateDao;

    /**
     * 获取带待出售商品
     * @param operate 查询条件
     * @return
     */
    public Page<Operate> findOperatePage(Operate operate, HttpServletRequest request, HttpServletResponse response){
        Page<Operate> page = this.findPage(new Page<Operate>(request, response), operate);
        Date now = new Date();
        for (int i = 0; i < page.getList().size(); i++) {
            if(DataEntity.STATE_FLAG_DELETE.equals(page.getList().get(i).getStateFlag().trim())){
                page.getList().get(i).setSellState(Constants.OPERATE_GOODS_SELL_STATE_BAND); //禁用状态
            } else if(now.getTime() > page.getList().get(i).getEndDate().getTime()){
                page.getList().get(i).setSellState(Constants.OPERATE_GOODS_SELL_STATE_BE_OVERDUE);//过期状态
            }else{
                page.getList().get(i).setSellState(Constants.OPERATE_GOODS_SELL_STATE_NORMAL);//正常状态
            }
        }
        return page;
    }

    /**
     * 保存，编辑数据
     * @param operate
     * @throws Exception
     */
    public void saveOperate(Operate operate) throws Exception{
        if(!operate.getIsNewRecord()){
            //从数据库取出记录的值
            Operate t = this.get(operate.getId());
            //将编辑表单中的非NULL值覆盖数据库记录中的值
            MyBeanUtils.copyBeanNotNull2Bean(operate, t);
            //编辑表单保存
            this.save(t);
        }else{
            //新增表单保存
            this.save(operate);
        }
    }

    /**
     * 批量删除数据
     * @param ids 删除数据的id
     * @return  成功条数
     */
    public int deleteAll(String ids){
        String idArray[] =ids.split(",");
        int effect = 0;
        for(String id : idArray){
            effect++;
            operateDao.delete(new Operate(id));
        }
        return effect;
    }

}
