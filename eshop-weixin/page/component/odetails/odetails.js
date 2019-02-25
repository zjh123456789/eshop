const app = getApp()
Page({
  data:{
    address:{},
    hasAddress: false,
    total:0,
    orders:[],
    btn1Text: '去付款',
    btn2Text: '取消订单'
  },
  
  onLoad: function (options){
    const self = this;
    wx.getStorage({
      key: 'sn',
      success: function (res) {
        self.getOrderDetail(res.data, options.orderId);
      },
    })
  },
  /**
   * 查看订单详情
   */
  getOrderDetail(sn, orderId){
    const self = this;
    wx.request({
      url: app.globalData.api.orderDetail,
      data: {
        sn : sn,
        orderId: orderId
      },
      success(res){
        self.setData({
          orders : res.data.data
        })
        self.initBtnText();
      }
    })
  },
  /**
   * 初始化按钮值
   */
  initBtnText(){
    const self = this;
    var text1 = (this.data.orders.tradeState == '已支付') ? '申请退款' : this.data.orders.tradeState;
    var text2 = (this.data.orders.orderState == '未完成') ? '取消订单' : this.data.orders.orderState;
    self.setData({
      btn1Text: text1,
      btn2Text: text2
    })
  },
  /**
   * 发起支付请求
   */
  payOrders() {
    var self = this;
    var orderId = this.data.orders.orderId;
    var sn = wx.getStorageSync("sn");
    wx.showModal({
      title: '支付提示',
      content: '只做支付演示，确认支付？',
      complete() {
        //模拟支付
        setTimeout(function () {

          wx.request({
            url: app.globalData.api.toPay,
            method: "POST",
            header: { "Content-Type": "application/x-www-form-urlencoded" },
            data: {
              sn: sn,
              orderId: orderId
            },
            success(res) {
              self.getOrderDetail(sn, orderId);
            }
          })

        }, 1000)
      }
    })
  },
  /**
   * 发起退款请求
   */
  refundOrders() {
    var self = this;
    var orderId = this.data.orders.orderId;
    var sn = wx.getStorageSync("sn");
    wx.showModal({
      title: '提示',
      content: '即将发起退款申请，确定要申请退款？',
      complete() {
        //模拟支付
        wx.request({
          url: app.globalData.api.toRefund,
          method: "POST",
          header: { "Content-Type": "application/x-www-form-urlencoded" },
          data: {
            sn: sn,
            orderId: orderId
          },
          success(res) {
            self.getOrderDetail(sn, orderId);
          }
        })
      }
    })
  },
  /**
   * 按钮1点击事件
   */
  btn1Click(e){
    if (this.data.btn1Text == '去付款'){
      this.payOrders();
    } else if (this.data.btn1Text == '申请退款'){
      this.refundOrders();
    }
  },
  /**
   * 按钮2点击事件
   */
  btn2Click(e){
    if (this.data.btn2Text == '取消订单') {
      this.cancelOrders();
    }
    
  },
  /**
   * 取消订单
   */
  cancelOrders(){
    var self = this;
    var orderId = this.data.orders.orderId;
    var sn = wx.getStorageSync("sn");
    wx.showModal({
      title: '提示',
      content: '确定要申请取消订单？',
      complete() {
        //模拟支付
        wx.request({
          url: app.globalData.api.toCancel,
          method: "POST",
          header: { "Content-Type": "application/x-www-form-urlencoded" },
          data: {
            sn: sn,
            orderId: orderId
          },
          success(res) {
            self.getOrderDetail(sn, orderId);
          }
        })
      }
    })
  }
})