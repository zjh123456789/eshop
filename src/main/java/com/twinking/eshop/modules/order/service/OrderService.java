package com.twinking.eshop.modules.order.service;

import com.twinking.eshop.common.base.entity.Page;
import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.utils.MyBeanUtils;
import com.twinking.eshop.common.utils.StringUtils;
import com.twinking.eshop.modules.integral.dao.IntegralDao;
import com.twinking.eshop.modules.integral.entity.Integral;
import com.twinking.eshop.modules.order.dao.OrderDao;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.order.entity.OrderDetail;
import com.twinking.eshop.modules.user.dao.UserDao;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description:  订单业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 13 29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderService extends AbstractService<OrderDao, Order> {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private IntegralDao integralDao;


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
            orderDao.delete(new Order(id));
        }
        return effect;
    }

    /**
     * 保存修改
     * @param order  订单
     */
    public String saveOrder(Order order) throws Exception{
        //不允许在后台创建表单
        if(order.getIsNewRecord()){
            return "修改订单失败";
        }
        //判断是否修改了完成日期
        if(StringUtils.isNotBlank(order.getChangFinishDate())){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            order.setFinishDate(simpleDateFormat.parse(order.getChangFinishDate()));
        }
        //从数据库取出记录的值
        Order t = orderDao.get(order.getId());
        //将编辑表单中的非NULL值覆盖数据库记录中的值
        MyBeanUtils.copyBeanNotNull2Bean(order, t);
        //编辑表单保存
        this.save(t);
        return "修改订单成功";
    }

    /**
     * 查看某用户订单详情列表
     * @param orderDetail 订单id
     * @return
     */
    public OrderDetail findOrderDetail(OrderDetail orderDetail){
        //设置根据订单id查找商品
        Integral integral = new Integral();
        integral.setOrderId(orderDetail.getOrderId());
        List<Integral> list = integralDao.findAllListByOrderIdIgnoreState(orderDetail.getOrderId());
        orderDetail.setGoodsList(list);
        return orderDetail;
    }

    /**
     * TODO 查询待处理订单
     * @param page 分页对象
     * @param order
     * @return
     */
    public Page<Order> findTodoPage(Page<Order> page,Order order) {
        order.setPage(page);
        List<Order> orders = orderDao.findTodoList(order);
        page.setList(orders);
        return page;
    }

    /**
     * TODO 查询未支付订单
     * @param page 分页对象
     * @param order
     * @return
     */
    public Page<Order> findUnFinishPage(Page<Order> page,Order order) {
        order.setPage(page);
        List<Order> orders = orderDao.findUnFinishList(order);
        page.setList(orders);
        return page;
    }

    /**
     * TODO 查询已完成订单
     * @param page 分页对象
     * @param order
     * @return
     */
    public Page<Order> findFinishPage(Page<Order> page,Order order) {
        order.setPage(page);
        List<Order> orders = orderDao.findFinishList(order);
        page.setList(orders);
        return page;
    }

    /**
     * 同意退款
     * @param order 订单id
     */
    public String refund(Order order){
        if(order == null || order.getId() == null){
            return "用户不存在";
        }
        Order o = orderDao.get(order.getId());
        //判断状态是否为退款中
        if(!Constants.ORDER_REFUNING_STATE.equals(o.getTradeState())){
            return "状态不合法";
        }
        //更新交易状态为已退款
        order.setTradeState(Constants.ORDER_ALREADY_REFUND_STATE);
        orderDao.updateTradeState(order);
        //更新订单状态为已取消
        order.setOrderState(Constants.ORDER_CANCEL_STATE);
        orderDao.updateOrderState(order);
        //积分详情失效
        userIntegralFailure(o);
        return "退款成功";
    }

    /**
     * 拒绝退款申请
     * @param order 订单id
     * @return
     */
    public String refuseRefund(Order order){
        if(order == null || order.getId() == null){
            return "用户不存在";
        }
        Order o = orderDao.get(order.getId());
        //判断状态是否为退款中
        if(!Constants.ORDER_REFUNING_STATE.equals(o.getTradeState())){
            return "状态不合法";
        }
        //更新交易状态为无法退款
        order.setTradeState(Constants.ORDER_UNABLE_REFUND_STATE);
        orderDao.updateTradeState(order);
        //更新订单状态为无法取消
        order.setOrderState(Constants.ORDER_UNABLE_CANCEL_STATE);
        orderDao.updateOrderState(order);
        return  "拒绝退款成功";
    }

    /**
     * 取消订单
     * @param order 订单id
     * @return
     */
    public String cancelOrder(Order order){
        if(order == null || order.getId() == null){
            return "用户不存在";
        }
        Order o = orderDao.get(order.getId());
        //判断状态是否为取消订单中
        if(!Constants.ORDER_CANCELING_STATE.equals(o.getOrderState())){
            return  "状态不合法";
        }
        //更新订单状态为已取消
        order.setOrderState(Constants.ORDER_CANCEL_STATE);
        orderDao.updateOrderState(order);
        //如果订单已支付或者退款中  该订单积分失效 用户积分变更
        if(Constants.ORDER_ALREADY_PAY_STATE.equals(o.getTradeState()) ||
                Constants.ORDER_REFUNING_STATE.equals(o.getTradeState())){
            //更新交易状态为已退款
            order.setTradeState(Constants.ORDER_ALREADY_REFUND_STATE);
            orderDao.updateTradeState(order);
            userIntegralFailure(o);
        }
        //设置订单结束时间
        order.setFinishDate(new Date());
        orderDao.setOrderCompletionTime(order);
        return "取消订单成功";
    }

    /**
     * 拒绝取消订单申请
     * @param order 订单id
     * @return
     */
    public String refuseCancelOrder(Order order){
        if(order == null || order.getId() == null){
            return  "用户不存在";
        }
        Order o = orderDao.get(order.getId());
        //判断状态是否为取消订单中
        if(!Constants.ORDER_CANCELING_STATE.equals(o.getOrderState())){
            return  "状态不合法";
        }
        //更新状态为订单无法取消
        order.setOrderState(Constants.ORDER_UNABLE_CANCEL_STATE);
        orderDao.updateOrderState(order);
        //如果已支付或者退款中 更新交易状态为无法退款
        if(Constants.ORDER_ALREADY_PAY_STATE.equals(order.getTradeState()) ||
                Constants.ORDER_REFUNING_STATE.equals(o.getTradeState())){
            order.setTradeState(Constants.ORDER_UNABLE_REFUND_STATE);
            orderDao.updateTradeState(order);
        }
        //设置订单结束时间
        order.setFinishDate(new Date());
        orderDao.setOrderCompletionTime(order);
        return  "拒绝取消订单成功";
    }

    /**
     * 使积分详情失效 扣除改订单积分
     * @param o 订单id 用户id
     */
    public void userIntegralFailure(Order o) {
        Integral integral = new Integral();
        integral.setUserId(o.getUserId());
        integral.setOrderId(o.getId());
        integralDao.integralFailure(integral);
        //用户积分变更
        User user = userDao.get(o.getUserId());
        double changeIntegral = user.getIntegral() - o.getTotalIntegral();
        user.setIntegral(changeIntegral);
        userDao.updateIntegralBySN(user);
        //缓存数据变更
        UserUtils.addUserToCache(user.getSn(), user);
    }
}
