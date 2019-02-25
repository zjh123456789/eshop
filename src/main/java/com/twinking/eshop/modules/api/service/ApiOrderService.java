package com.twinking.eshop.modules.api.service;

import com.fasterxml.jackson.databind.JavaType;
import com.twinking.eshop.common.base.service.AbstractService;
import com.twinking.eshop.common.constant.Constants;
import com.twinking.eshop.common.exceptions.UnauthorizedException;
import com.twinking.eshop.common.mapper.JsonMapper;
import com.twinking.eshop.common.utils.IdGenUtils;
import com.twinking.eshop.modules.goods.dao.GoodsDao;
import com.twinking.eshop.modules.goods.entity.Goods;
import com.twinking.eshop.modules.integral.dao.IntegralDao;
import com.twinking.eshop.modules.integral.entity.Integral;
import com.twinking.eshop.modules.order.dao.OrderDao;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.order.uitls.OrderUtils;
import com.twinking.eshop.modules.user.dao.UserDao;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.user.utils.UserUtils;
import com.twinking.eshop.modules.api.dao.ApiOperateDao;
import com.twinking.eshop.modules.api.entity.order.OrderDetailsGoods;
import com.twinking.eshop.modules.api.entity.order.OrderRequest;
import com.twinking.eshop.modules.api.entity.order.WeChartOrderDetail;
import com.twinking.eshop.modules.api.entity.order.WeChartOrderList;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoodsDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 微信订单业务层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/26 13 41
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiOrderService extends AbstractService<OrderDao, Order> {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApiOperateDao apiOperateDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private IntegralDao integralDao;

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 下单
     * @param sn 请求标识码
     * @param orderData 订单数据 包括商品ID和数量
     * @param address 收货地址
     * @throws NullPointerException 空指针异常
     * @throws UnauthorizedException sn未授权
     * @throws ClassCastException Json类型转换错误
     */
    public void order(String sn, String orderData,String address) throws
            NullPointerException, UnauthorizedException, ClassCastException{
        //参数校验
        validationParameters(sn, orderData, address);
        //根据sn获取用户
        User user = getUserBySN(sn, userDao);
        //json转订单商品数组
        JavaType javaType = JsonMapper.getInstance().createCollectionType(ArrayList.class,OrderRequest.class, OrderRequest.class);
        List<OrderRequest> list = JsonMapper.getInstance().fromJson(orderData, javaType);
        //创建订单和生成积分详情
        List<Integral> integralList = new ArrayList<Integral>();
        Order order = createOrder(user, list, address, integralList);
        //将订单和积分更新到数据库
        orderDao.insert(order);
        for (Integral integral : integralList){
            integralDao.insert(integral);
        }
    }

    /**
     * 获取订单列表
     * @return
     */
    public List<WeChartOrderList> getWeChartOrderList(String sn){
        //参数校验
        validationParameters(sn);
        //根据sn获取用户
        User user = getUserBySN(sn, userDao);
        List<WeChartOrderList> weChartOrderLists = orderDao.getWeChartOrderListUserId(user.getId());
        for (int i = 0; i < weChartOrderLists.size(); i++) {
            Integral integral = integralDao.findByOrderIdLimitOne(weChartOrderLists.get(i).getOrderId());
            weChartOrderLists.get(i).setGoodsName(integral.getGoodsName());
            weChartOrderLists.get(i).setGoodsPicture(integral.getGoodsPicture());
            weChartOrderLists.get(i).setNumber(integral.getGoodsByNumber());
            weChartOrderLists.get(i).setGoodsStandard(integral.getGoodsStandardName());
        }
        return weChartOrderLists;
    }

    /**
     * 创建订单和生成积分
     * @param user 用户
     * @param list 购买商品结合
     * @param address 地址
     *@param integralList 积分详情集合
     * @return 积分详情
     */
    private Order createOrder(User user, List<OrderRequest> list, String address, List<Integral> integralList) {
        Order order = new Order();
        order.setId(IdGenUtils.uuid());
        order.setOrderNumber(OrderUtils.createOrderNumber());
        order.setUserId(user.getId());
        order.setCreateDate(new Date());
        order.setOrderState(Constants.ORDER_UN_FINISH_STATE);
        order.setTradeState(Constants.ORDER_UN_PAY_STATE);
        //计算订单总价和商品积分
        double totalPrice = 0;
        double totalIntegral= 0;
        for (OrderRequest orderRequest : list){
            Integral integral = createIntegral(user, orderRequest, order);
            totalPrice += integral.getGoodsTotalPrice();
            totalIntegral += integral.getChangeIntegral();
            integralList.add(integral);
        }
        order.setTotalPrice(totalPrice);
        order.setTotalIntegral(totalIntegral);
        order.setAddress(address);
        return order;
    }

    /**
     * 返回积分详情
     * @param user 购买用户
     * @param orderRequest 购买商品id和数量
     * @param order 订单
     * @return
     */
    private Integral createIntegral(User user, OrderRequest orderRequest, Order order){
        //获取购买商品详情
        SellGoodsDetail goods = apiOperateDao.getSellGoodsDetail(orderRequest.getGoodId());
        Integral integral = new Integral(user, goods, order, orderRequest);
        //商品库存变更
        int inventory = (int)goods.getInventory() - orderRequest.getNumber();
        Goods g = new Goods(orderRequest.getGoodId());
        g.setInventory(inventory);
        goodsDao.updateInventory(g);
        return integral;
    }

    /**
     * 订单支付
     * @param sn 用户标识
     * @param orderId 订单id
     */
    public void orderToPay(String sn, String orderId) {
        validationParameters(sn, orderId);
        User user = getUserBySN(sn, userDao);
        Order order = orderDao.get(orderId);
        //确保订单属于该用户
        ensureOrdersBelongToUser(sn, user, order);
        if(Constants.ORDER_UN_PAY_STATE.equals(order.getTradeState())){
            order.setTradeState(Constants.ORDER_ALREADY_PAY_STATE);
            order.setOrderState(Constants.ORDER_ALREADY_FINISH_STATE);
            order.setFinishDate(new Date());
            orderDao.update(order);
        }
        //积分详情生效
        Integral integral = new Integral();
        integral.setUserId(user.getId());
        integral.setOrderId(orderId);
        integralDao.integrationIntoEffect(integral);
        //用户积分变更
        double changeIntegral = order.getTotalIntegral() + user.getIntegral();
        user.setIntegral(changeIntegral);
        userDao.updateIntegralBySN(user);
        //同时更新缓存
        UserUtils.addUserToCache(sn, user);
    }

    /**
     * 退款申请
     * @param sn 用户标识
     * @param orderId 订单id
     */
    public void orderToRefund(String sn, String orderId) {
        validationParameters(sn, orderId);
        User user = getUserBySN(sn, userDao);
        Order order = orderDao.get(orderId);
        //确保订单属于该用户
        ensureOrdersBelongToUser(sn, user, order);
        if(Constants.ORDER_ALREADY_PAY_STATE.equals(order.getTradeState())){
            //更新交易状态为退款中
            order.setTradeState(Constants.ORDER_REFUNING_STATE);
            orderDao.updateTradeState(order);
        }
        //更新订单状态为取消中
        order.setOrderState(Constants.ORDER_CANCELING_STATE);
        orderDao.updateOrderState(order);
    }

    /**
     * 取消订单
     * @param sn 用户标识
     * @param orderId 订单id
     */
    public void cancelOrder(String sn, String orderId){
        validationParameters(sn, orderId);
        User user = getUserBySN(sn, userDao);
        Order order = orderDao.get(orderId);
        //确保订单属于该用户
        ensureOrdersBelongToUser(sn, user, order);
        //如果订单未支付则直接取消
        if(Constants.ORDER_UN_PAY_STATE.equals(order.getTradeState())){
            order.setOrderState(Constants.ORDER_CANCEL_STATE);
        }else {
            //否则更新订单状态为取消中
            order.setOrderState(Constants.ORDER_CANCELING_STATE);
        }
        orderDao.updateOrderState(order);
    }

    /**
     * 订单详情
     * @param sn 用户标识
     * @param orderId 订单id
     * @return
     */
    public WeChartOrderDetail getOrderDetail(String sn, String orderId) {
        validationParameters(sn, orderId);
        User user = getUserBySN(sn, userDao);
        Order order = orderDao.get(orderId);
        //确保订单属于该用户
        ensureOrdersBelongToUser(sn, user, order);
        WeChartOrderDetail weChartOrderDetail = new WeChartOrderDetail(order);
        List<Integral> list = integralDao.findAllListByOrderIdIgnoreState(orderId);
        for (int i = 0; i < list.size(); i++) {
            Integral integral = list.get(i);
            OrderDetailsGoods orderDetailsGoods = new OrderDetailsGoods(integral);
            weChartOrderDetail.getGoodsList().add(orderDetailsGoods);
        }
        return weChartOrderDetail;
    }
}
