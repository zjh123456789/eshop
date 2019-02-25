package com.twinking.eshop.modules.integral.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.utils.IdGenUtils;
import com.twinking.eshop.common.utils.excel.annotation.ExcelField;
import com.twinking.eshop.modules.order.entity.Order;
import com.twinking.eshop.modules.user.entity.User;
import com.twinking.eshop.modules.api.entity.order.OrderRequest;
import com.twinking.eshop.modules.api.entity.sellgoods.SellGoodsDetail;

import java.util.Date;

/**
 * @Description:  积分信息实体类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/19 22 31
 */
public class Integral extends DataEntity<Integral> {

    /**
     * 用户名
     */
    private String username;
    /**
     * 积分变更
     */
    private Double changeIntegral;
    /**
     * 当前积分
     */
    private Double currentIntegral;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 展示链接
     */
    private String goodsPicture;
    /**
     * 商品类型
     */
    private String goodsTypeName;
    /**
     * 原价（￥）
     */
    private Double goodsOriginalPrice;
    /**
     * 售价（￥）
     */
    private Double goodsSellPrice;
    /**
     * 商品规格
     */
    private String goodsStandardName;
    /**
     * 购买数量
     */
    private Integer goodsByNumber;
    /**
     * 总价
     */
    private Double goodsTotalPrice;
    /**
     * 商品介绍
     */
    private String goodsIntroduce;
    /**
     * 记录时间
     */
    private Date recordDate;

    public Integral() {
    }

    public Integral(String id) {
        super(id);
    }

    /**
     * 构造函数
     * @param user 购买者
     * @param goods 商品
     * @param order 订单
     * @param orderRequest 创建订单请求参数
     */
    public Integral(User user, SellGoodsDetail goods, Order order, OrderRequest orderRequest) {
        this.setId(IdGenUtils.uuid());
        double thisPrice = goods.getSellPrice() * orderRequest.getNumber();
        double thisIntegral = goods.getIntegral() * orderRequest.getNumber();
        this.setUserId(user.getId());
        this.setUsername(user.getUsername());
        this.setOrderId(order.getId());
        this.setOrderNumber(order.getOrderNumber());
        this.setChangeIntegral(thisIntegral);
        this.setCurrentIntegral(user.getIntegral() + thisIntegral);
        this.setGoodsName(goods.getName());
        this.setGoodsPicture(goods.getPicture());
        this.setGoodsOriginalPrice(goods.getOriginalPrice());
        this.setGoodsSellPrice(goods.getSellPrice());
        this.setGoodsStandardName(goods.getStandard());
        this.setGoodsTypeName(goods.getType());
        this.setGoodsByNumber(orderRequest.getNumber());
        this.setGoodsTotalPrice(thisPrice);
        this.setGoodsIntroduce(goods.getIntroduce());
        this.setRecordDate(new Date());
        //只有支付完成积分才生效
        this.setStateFlag(STATE_FLAG_DELETE);
    }

    @ExcelField(title="用户名", align=2, sort=1)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ExcelField(title="积分变更", align=2, sort=2)
    public Double getChangeIntegral() {
        return changeIntegral;
    }

    public void setChangeIntegral(Double changeIntegral) {
        this.changeIntegral = changeIntegral;
    }

    @ExcelField(title="当前积分", align=2, sort=3)
    public Double getCurrentIntegral() {
        return currentIntegral;
    }

    public void setCurrentIntegral(Double currentIntegral) {
        this.currentIntegral = currentIntegral;
    }

    @ExcelField(title="用户ID", align=2, sort=4)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @ExcelField(title="订单号", align=2, sort=5)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @ExcelField(title="商品名", align=2, sort=6)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @ExcelField(title="展示链接", align=2, sort=7)
    public String getGoodsPicture() {
        return goodsPicture;
    }

    public void setGoodsPicture(String goodsPicture) {
        this.goodsPicture = goodsPicture;
    }

    @ExcelField(title="商品类型", align=2, sort=8)
    public String getGoodsTypeName() {
        return goodsTypeName;
    }

    public void setGoodsTypeName(String goodsTypeName) {
        this.goodsTypeName = goodsTypeName;
    }

    @ExcelField(title="原价（￥）", align=2, sort=9)
    public Double getGoodsOriginalPrice() {
        return goodsOriginalPrice;
    }

    public void setGoodsOriginalPrice(Double goodsOriginalPrice) {
        this.goodsOriginalPrice = goodsOriginalPrice;
    }

    @ExcelField(title="售价（￥）", align=2, sort=10)
    public Double getGoodsSellPrice() {
        return goodsSellPrice;
    }

    public void setGoodsSellPrice(Double goodsSellPrice) {
        this.goodsSellPrice = goodsSellPrice;
    }

    @ExcelField(title="商品规格", align=2, sort=11)
    public String getGoodsStandardName() {
        return goodsStandardName;
    }

    public void setGoodsStandardName(String goodsStandardName) {
        this.goodsStandardName = goodsStandardName;
    }

    @ExcelField(title="购买数量", align=2, sort=12)
    public Integer getGoodsByNumber() {
        return goodsByNumber;
    }

    public void setGoodsByNumber(Integer goodsByNumber) {
        this.goodsByNumber = goodsByNumber;
    }

    @ExcelField(title="总价", align=2, sort=13)
    public Double getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(Double goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    @ExcelField(title="商品介绍", align=2, sort=14)
    public String getGoodsIntroduce() {
        return goodsIntroduce;
    }

    public void setGoodsIntroduce(String goodsIntroduce) {
        this.goodsIntroduce = goodsIntroduce;
    }

    @ExcelField(title="记录时间", align=2, sort=15)
    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}
