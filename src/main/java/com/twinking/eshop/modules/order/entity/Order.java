package com.twinking.eshop.modules.order.entity;

import com.twinking.eshop.common.base.entity.DataEntity;
import com.twinking.eshop.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * @Description:  订单基类
 * @Autuor 朱景辉（1229585753@qq.com）
 * @Date 2018/10/20 09 38
 */
public class Order  extends DataEntity<Order> {
    private static final long serialVersionUID = 1L;
    /**
     * 订单号
     */
    private String orderNumber;
    /**
     * 产生订单用户ID
     */
    private String userId;
    /**
     * 产生订单用户名
     */
    private String username;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 完成时间
     */
    private Date finishDate;
    /**
     * 修改完成时间
     */
    private String changFinishDate;
    /**
     * 交易状态
     */
    private String tradeState;
    /**
     * 订单状态
     */
    private String orderState;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 积分
     */
    private Double totalIntegral;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 订单类型
     */
    private Integer listType;

    public Order() {
    }

    public Order(String id) {
        super(id);
    }

    public String getUserId() {
        return userId;
    }
    @ExcelField(title="订单号", align=2, sort=1)

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @ExcelField(title="用户名", align=2, sort=2)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ExcelField(title="创建时间", align=2, sort=3)
    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @ExcelField(title="完成时间", align=2, sort=4)
    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @ExcelField(title="交易状态", align=2, sort=5)
    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    @ExcelField(title="订单状态", align=2, sort=6)
    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }


    @ExcelField(title="总价", align=2, sort=7)
    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ExcelField(title="积分", align=2, sort=8)
    public Double getTotalIntegral() {
        return totalIntegral;
    }

    public void setTotalIntegral(Double totalIntegral) {
        this.totalIntegral = totalIntegral;
    }

    @ExcelField(title="收货地址", align=2, sort=9)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ExcelField(title="备注", align=2, sort=10)
    @Override
    public String getRemarks() {
        return remarks;
    }

    @Override
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getChangFinishDate() {
        return changFinishDate;
    }

    public void setChangFinishDate(String changFinishDate) {
        this.changFinishDate = changFinishDate;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }
}
