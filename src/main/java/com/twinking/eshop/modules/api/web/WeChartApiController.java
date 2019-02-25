package com.twinking.eshop.modules.api.web;

import com.twinking.eshop.common.base.json.JSON;
import com.twinking.eshop.modules.api.entity.order.WeChartOrderDetail;
import com.twinking.eshop.modules.api.entity.order.WeChartOrderList;
import com.twinking.eshop.modules.api.entity.sellgoods.GoodsList;
import com.twinking.eshop.modules.api.entity.sellgoods.GoodsTypeList;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoodsDetail;
import com.twinking.eshop.modules.api.entity.user.SessionRequest;
import com.twinking.eshop.modules.api.entity.user.SessionResult;
import com.twinking.eshop.modules.api.entity.user.UserInfo;
import com.twinking.eshop.modules.api.service.ApiOperateService;
import com.twinking.eshop.modules.api.service.ApiOrderService;
import com.twinking.eshop.modules.api.service.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:  微信端控制层
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/23 09 26
 */
@Controller
@CrossOrigin
@RequestMapping("api/wx")
public class WeChartApiController {

    @Autowired
    private ApiUserService weiXinUserService;

    @Autowired
    private ApiOperateService apiOperateService;

    @Autowired
    private ApiOrderService apiOrderService;

    /**
     * 根据用户传进来的code去微信服务器获取用户唯一标示  如未注册自动注册
     * @param sessionRequest 请求数据 code 昵称 性别
     * @return
     */
    @ResponseBody
    @RequestMapping("sn")
    public JSON<SessionResult> getSession(SessionRequest sessionRequest){
        SessionResult sessionResult = weiXinUserService.getSession(sessionRequest);
        return new JSON<>(sessionResult);
    }

    /**
     * 根据商品id获取在售商品详细信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("goods_detail")
    public JSON<SellGoodsDetail> getSellGoodsDetail (String id){
        SellGoodsDetail sellGoodsDetail = apiOperateService.getSellGoodsDetail(id);
        return new JSON<>(sellGoodsDetail);
    }

    /**
     * 获取10个最新商品
     * @return
     */
    @ResponseBody
    @RequestMapping("new_list")
    public JSON<GoodsList> getNewGoodsList (){
        GoodsList goodsList = apiOperateService.findNewGoodsList();
        return new JSON<>(goodsList);
    }

    /**
     * 获取推荐商品
     * @return
     */
    @ResponseBody
    @RequestMapping("recommend_list")
    public JSON<GoodsList> getRecommendGoodsList (){
        GoodsList goodsList = apiOperateService.findRecommendGoodsList();
        return new JSON<>(goodsList);
    }


    /**
     * 获取首发商品
     * @return
     */
    @ResponseBody
    @RequestMapping("first_list")
    public JSON<GoodsList> getFirstGoodsList (){
        GoodsList goodsList = apiOperateService.findFirstGoodsList();
        return new JSON<>(goodsList);
    }


    /**
     * 获取限时抢购商品
     * @return
     */
    @ResponseBody
    @RequestMapping("rush_list")
    public JSON<GoodsList> getRushGoodsList (){
        GoodsList goodsList = apiOperateService.findRushGoodsList();
        return new JSON<>(goodsList);
    }

    /**
     * 获取所有商品类别  不含未分类
     * @return
     */
    @ResponseBody
    @RequestMapping("goods_type_list")
    public JSON<GoodsTypeList> goodsTypeList (){
        GoodsTypeList goodsTypeList = apiOperateService.findGoodsTypeList();
        return new JSON(goodsTypeList);
    }

    /**
     * 获取基本用户信息
     * @param sn
     * @return
     */
    @ResponseBody
    @RequestMapping("user_info")
    public JSON<UserInfo> getUserInfo (String sn){
        UserInfo userInfo = weiXinUserService.findUserInfoBySN(sn);
        return new JSON(userInfo);
    }

    /**
     * 获取用户积分
     * @param sn
     * @return 积分
     */
    @ResponseBody
    @RequestMapping("user_integral")
    public JSON<Double> getUserIntegral (String sn){
        double integral = weiXinUserService.findIntegralBySN(sn);
        return new JSON(integral);
    }

    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping("update_user_info")
    public JSON<String> updateUserInfo (UserInfo userInfo) throws Exception{
        weiXinUserService.updateUserInfoBySN(userInfo);
        return new JSON();
    }

    /**
     * 下单
     * @param sn 请求标识码
     * @param orderData 订单数据 包括商品ID和数量
     * @param address 收货地址
     * @return 无
     */
    @ResponseBody
    @RequestMapping(value = "order", method = RequestMethod.POST)
    public JSON<Object> order(String sn, String orderData, String address) {
        apiOrderService.order(sn, orderData, address);
        return new JSON();
    }

    /**
     * 获取订单列表 只显示订单第一件商品
     * @param sn 请求标识码
     * @return 订单数据
     */
    @ResponseBody
    @RequestMapping("order_list")
    public JSON<List<WeChartOrderList>> orderList(String sn){
        List<WeChartOrderList> data = apiOrderService.getWeChartOrderList(sn);
        return new JSON(data);
    }

    /**
     * 订单支付 只做演示
     * @param sn 用户标识
     * @param orderId 订单id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pay", method = RequestMethod.POST)
    public JSON<String> pay (String sn ,String orderId){
        apiOrderService.orderToPay(sn, orderId);
        return new JSON();
    }

    /**
     * 订单退款
     * @param sn 用户标识
     * @param orderId 订单id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "refund", method = RequestMethod.POST)
    public JSON<String> refund (String sn ,String orderId){
        apiOrderService.orderToRefund(sn, orderId);
        return new JSON();
    }

    /**
     * 取消订单
     * @param sn 用户标识
     * @param orderId 订单id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "cancel", method = RequestMethod.POST)
    public JSON<String> cancel (String sn ,String orderId){
        apiOrderService.cancelOrder(sn, orderId);
        return new JSON();
    }

    /**
     * 订单详情
     * @param sn 用户标识
     * @param orderId 订单id
     * @return
     */
    @ResponseBody
    @RequestMapping("order_detail")
    public JSON<WeChartOrderDetail> orderDetail(String sn , String orderId){
        WeChartOrderDetail weChartOrderDetail = apiOrderService.getOrderDetail(sn, orderId);
        return new JSON(weChartOrderDetail);
    }



}
