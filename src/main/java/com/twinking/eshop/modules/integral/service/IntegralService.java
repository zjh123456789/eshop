package com.twinking.eshop.modules.integral.service;

import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.modules.integral.dao.IntegralDao;
import com.twinking.eshop.modules.integral.entity.Integral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:  积分详情业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IntegralService  extends AbstractService<IntegralDao, Integral> {

    @Autowired
    private IntegralDao integralDao;

    /**
     * 获得用户id全部数据
     * @param userId
     * @return
     */
    public List<Integral> findAllListByUserId(String userId){
        return integralDao.findAllListByUserId(userId);
    }

}
