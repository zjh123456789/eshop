package com.twinking.eshop.modules.order.dao;

import com.twinking.eshop.common.base.dao.AbstractDao;
import com.twinking.eshop.common.base.dao.MyBatisDao;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.api.entity.order.WeChartOrderList;

import java.util.List;

/**
 * @Description:  订单持久层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 13 28
 */
@MyBatisDao
public interface OrderDao extends AbstractDao<Order> {

    /**
     * 查询待处理订单
     * @param order
     * @return
     */
    public List<Order> findTodoList(Order order);

    /**
     * 查询未支付订单
     * @param order
     * @return
     */
    public List<Order> findUnFinishList(Order order);

    /**
     * 查询已完成订单
     * @param order
     * @return
     */
    public List<Order> findFinishList(Order order);

    /**
     * 修改支付状态
     * @param order 订单id
     * @return
     */
    public int updateTradeState(Order order);

    /**
     * 修改订单完成状态
     * @param order 订单id
     * @return
     */
    public int updateOrderState(Order order);

    /**
     * 获取订单列表
     * @return
     */
    public List<WeChartOrderList> getWeChartOrderListUserId(String userId);

    /**
     * 设置订单完成时间
     * @param order
     * @return
     */
    public int setOrderCompletionTime(Order order);
}
