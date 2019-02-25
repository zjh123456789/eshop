package com.twinking.eshop.modules.integral.dao;

import com.twinking.eshop.common.base.dao.AbstractDao;
import com.twinking.eshop.common.base.dao.MyBatisDao;
import com.twinking.eshop.modules.integral.entity.Integral;

import java.util.List;

/**
 * @Description:  积分详情持久层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 23 03
 */
@MyBatisDao
public interface IntegralDao extends AbstractDao<Integral> {

    /**
     * 获得订单id全部数据
     * @param orderId
     * @return
     */
    public List<Integral> findAllListByOrderId(String orderId);

    /**
     * 获得订单id全部数据 忽略状态码
     * @param orderId
     * @return
     */
    public List<Integral> findAllListByOrderIdIgnoreState(String orderId);

    /**
     * 获得订单id一个数据
     * @param orderId
     * @return
     */
    public Integral findByOrderIdLimitOne(String orderId);

    /**
     * 获得用户id全部数据
     * @param userId
     * @return
     */
    public List<Integral> findAllListByUserId(String userId);

    /**
     * 积分生效 支付订单
     * @param integral 用户ID和订单ID
     */
    public void integrationIntoEffect(Integral integral);

    /**
     * 积分失效 取消订单或者退款
     * @param integral 用户ID和订单ID
     */
    public void integralFailure(Integral integral);

}
